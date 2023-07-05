package com.example.finalproject.configuration;

import com.example.finalproject.entity.DbSettings;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class SpringJdbcConfig {
    @Bean
    public DataSource postgresqlDataSource() {
//        DbSettings dbSettings = readSetting();
        String user = readSetting().getUser();
        String password = readSetting().getPassword();
        String dbName = readSetting().getDbName();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/"+ dbName);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    public static DbSettings readSetting() {
        DbSettings dbSettings = new DbSettings();
        try {
            File file = new File("src/main/resources/dataBaseSettings.xlsx");
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            iterator.next();
            Row row = iterator.next();
            Cell cell2 = sheet.getRow(1).getCell(1);
            //SET AS STRING TYPE
            cell2.setCellType(CellType.STRING);
            dbSettings.setUser(row.getCell(0).getStringCellValue());
            dbSettings.setPassword(String.valueOf(cell2.getStringCellValue()));
            dbSettings.setDbName(row.getCell(2).getStringCellValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbSettings;
    }
}
