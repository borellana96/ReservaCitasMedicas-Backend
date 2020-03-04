
package com.clinica.service;

import com.clinica.model.ResultadoCargaMasivaHorario;
import com.clinica.util.TypesUtil;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import javax.sql.DataSource;

import com.clinica.model.Horario;
import com.clinica.model.Medico;
@Service
public class CargaMasivaHorarioService implements ICargaMasivaHorarioService {
     
    // private static final String PROCESO_NO_ENCONTRADO = "El proceso de carga %s no fue encontrado";
     //private static final String PROCESO_NAME = "CARG_MIS_V";
     private @Autowired
     DataSource dataSource;

     public CargaMasivaHorarioService(){
     }
    
    
     @Override
     @Transactional(propagation = Propagation.REQUIRED)
     public List<ResultadoCargaMasivaHorario> cargarArchivos(List<MultipartFile> multipartfiles) {
          List<ResultadoCargaMasivaHorario> listaResultados = new ArrayList<>();
          for (MultipartFile multipartfile : multipartfiles) {
               String filename = multipartfile.getOriginalFilename();
               try (BufferedInputStream bis = new BufferedInputStream(multipartfile.getInputStream())) {
                    ResultadoCargaMasivaHorario resultadoCarga = leerExcel(filename, bis);
                    listaResultados.add(resultadoCarga);
               } catch (IOException e) {
                    e.printStackTrace();
                    //throw new RecursoNoEncontradoException(e.getMessage());
               }
          }
          return listaResultados;
     }

     @Transactional(propagation = Propagation.REQUIRED)
     public ResultadoCargaMasivaHorario leerExcel(String filename, InputStream inputStream){
          try (Workbook workbook = WorkbookFactory.create(inputStream)) {
               Sheet sheet = workbook.getSheetAt(0);
               Iterator<Row> rowIterator = sheet.iterator();
               Row row;
               rowIterator.next();
               List<Horario> listaFilas = new ArrayList<>();
               while (rowIterator.hasNext()) {
                    row = rowIterator.next();
                    int idHorario  = TypesUtil.getNumericValue(row.getCell(1)).intValue();           
                    String dia     =    TypesUtil.getStringValue(row.getCell(2)); 
                    Time horaInicio     = new Time(TypesUtil.getDateValue(row.getCell(3), "HH:mm").getTime());
                    Time horaFin        = new Time(TypesUtil.getDateValue(row.getCell(4), "HH:mm").getTime());
                    int medicoId        = TypesUtil.getNumericValue(row.getCell(5)).intValue();   
                    System.out.println("IDHORARIO "+idHorario);  
                    System.out.println("DIA "+dia);  
                    System.out.println("HORAR INICIO "+horaInicio);  
                    
                    System.out.println("HORA FIN "+horaFin); 
                    System.out.println("MEDICOCID "+medicoId); 
                   // horaInicio=horaInicio-05:00;
                     Horario a = new Horario();
                     Medico m = new Medico();
                     m.setIdMedico(medicoId);
                 a.setIdHorario(idHorario);
                 a.setDia(dia);
                 a.setHoraInicio(horaInicio);
                 a.setHoraFin(horaFin);
                 a.setMedico_id(m);
                    
                    listaFilas.add(a);
               }
               cargarExcel(listaFilas);
                  ResultadoCargaMasivaHorario a = new ResultadoCargaMasivaHorario();
                    a.setNombreArchivo(filename);
                    a.setNumeroRegistros(listaFilas.size());
                    a.setExito(true);

               return a;
          } catch (IOException ex) {
               ex.printStackTrace();
            ResultadoCargaMasivaHorario a = new ResultadoCargaMasivaHorario();
              a.setNombreArchivo(filename);
              a.setNumeroRegistros(0);
              a.setExito(false);
               return a;
               //throw new ReadingExcelFileException(
               //                  "Asegúrese de que se trata de un archivo Excel. Nombre de archivo: " + filename);
          } catch (Exception ex) {
               ex.printStackTrace();
            ResultadoCargaMasivaHorario a = new ResultadoCargaMasivaHorario();
              a.setNombreArchivo(filename);
              a.setNumeroRegistros(0);
              a.setExito(false);
               return a;
          }
     }

     @Transactional(propagation = Propagation.REQUIRED)
     public void cargarExcel(List<Horario> listaFilas){
          int batchSize = 1000;
          if(listaFilas.size()<=0){
               return;
          }
          try(Connection conn = dataSource.getConnection()){
               conn.setAutoCommit(false);
               try{
                    PreparedStatement stmtDelete = conn.prepareStatement("DELETE FROM HORARIO ");
                    
                    stmtDelete.addBatch();
                    stmtDelete.executeBatch();
                    conn.commit();
                    try{
                         PreparedStatement stmt = conn.prepareStatement(
                              "INSERT INTO HORARIO("+
                                   "id_horario          ," +
                                   "dia       ," +
                                   "hora_fin   ," +   
                                   "hora_inicio                 ," +
                                   "medico_id               "  +
                              ") VALUES ("+
                                   "?,"+
                                   "?,"+
                                   "?,"+
                                   "?,"+
                                   "?"+
                              ")") ;
                         int[] idx = { 0 };
                         Iterator<Horario> itFacturaVisa = listaFilas.iterator();
                         Horario facturaVisa;
                         while(itFacturaVisa.hasNext()){
                              facturaVisa = itFacturaVisa.next();
                              try{
                                   TypesUtil.validarBDInteger(stmt,  1, facturaVisa.getIdHorario()  )    ;
                                   TypesUtil.validarBDString(stmt,  2, facturaVisa.getDia()  )  ;
                                   TypesUtil.validarBDTime(stmt,  3, facturaVisa.getHoraFin()       );
                                   TypesUtil.validarBDTime(stmt,  4, facturaVisa.getHoraInicio()        )  ;
                                   TypesUtil.validarBDInteger(stmt,  5, facturaVisa.getMedico_id().getIdMedico() )  ;
                                   
                                   stmt.addBatch();
                                   idx[0]++;
                                   if (idx[0] % batchSize == 0 ) {
                                        stmt.executeBatch();
                                        conn.commit();
                                        stmt.clearBatch();
                                        stmt.clearParameters();
                                        idx[0] = 0;
                                   }
                              }catch(SQLException e){
                                   if (conn != null) {
                                        try {
                                             conn.rollback();
                                        } catch (Exception ex) {
                                             ex.printStackTrace();
                                        }
                                   }
                              }
                         }

                         if(idx[0]>0){
                              stmt.executeBatch();
                              conn.commit();
                         }
                         
                         
                    }catch(SQLException e){
                         if (conn != null) {
                              try {
                                   conn.rollback();
                              } catch (Exception ex) {
                                   ex.printStackTrace();
                              }
                         }
                         e.printStackTrace();
                    }
               }catch(SQLException e) {
                    if (conn != null) {
                         try {
                              conn.rollback();
                         } catch (Exception ex) {
                              ex.printStackTrace();
                         }
                    }
                    e.printStackTrace();
               }
          }catch (SQLException e ){
               e.printStackTrace();
          }
     }
     
}
