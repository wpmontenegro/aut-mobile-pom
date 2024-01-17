package com.mobile.util;

import com.mobile.exceptions.AutomationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mobile.util.Constants.RUTA_DATA_CSV;

public class CSVReader {

    private static boolean isFilterEnabled;
    private static int filterColumnIndex;
    public static final String FILTER_COLUMN_DEFAULT_NAME = "filter";
    private static final String FILTER_COLUMN_DEFAULT_VALUE = "true";

    private CSVReader() {
    }

    /**
     * Lee un valor aleatorio de una columna específica en un archivo CSV. Si se habilita el filtro
     * (mediante el campo "filter" en el CSV), se leerán solo los valores que cumplan con el filtro.
     * Para que una línea sea tomada, el campo "filter" debe contener "true"; de lo contrario, la línea
     * será ignorada.
     *
     * @param csvFilename  Nombre del archivo CSV a leer (sin la extensión .csv).
     * @param targetColumn Nombre de la columna objetivo de la que se leerá el valor.
     * @return Valor aleatorio de la columna especificada, sujeto al filtro si está habilitado.
     * @throws AutomationException Si se produce un error al leer el archivo CSV o si no se encuentra
     *                                  la columna especificada.
     */
    public static String readRandomValueFromCSV(String csvFilename, String targetColumn) {
        isFilterEnabled = false;
        filterColumnIndex = -1;
        List<String> targetColumnData = getDataFromTargetColumn(csvFilename, targetColumn, FILTER_COLUMN_DEFAULT_NAME, FILTER_COLUMN_DEFAULT_VALUE);
        if (!targetColumnData.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(targetColumnData.size());
            return targetColumnData.get(randomIndex);
        } else {
            throw new AutomationException("No data found or CSV is empty");
        }
    }

    /**
     * Lee un valor específico de una columna en un archivo CSV, aplicando un filtro personalizado definido por el usuario.
     * Solo se tomará la primera línea que cumplan con el filtro proporcionado. Para que una línea sea considerada, el valor en
     * la columna de filtro debe coincidir con el valor especificado por el usuario.
     *
     * @param csvFilename       Nombre del archivo CSV a leer (sin la extensión .csv).
     * @param targetColumn      Nombre de la columna de la que se leerá el valor.
     * @param filterColumnName  Nombre de la columna utilizada como filtro personalizado.
     * @param filterColumnValue Valor que debe coincidir en la columna de filtro para que la línea sea considerada.
     * @return Valor de la columna especificada, sujeto al filtro personalizado proporcionado.
     * @throws AutomationException Se lanza si ocurre un error al leer el archivo CSV, si no se encuentra la columna especificada o si el archivo está vacío.
     */
    public static String readValueFromCSV(String csvFilename, String targetColumn, String filterColumnName, String filterColumnValue) {
        isFilterEnabled = true;
        filterColumnIndex = -1;
        List<String> targetColumnData = getDataFromTargetColumn(csvFilename, targetColumn, filterColumnName, filterColumnValue);
        if (!targetColumnData.isEmpty()) {
            return targetColumnData.get(0);
        } else {
            throw new AutomationException("No data found or CSV is empty");
        }
    }

    private static List<String> getDataFromTargetColumn(String csvFileName, String targetColumn, String filterColumnName, String filterColumnValue) {
        String csvFilePath = String.format(RUTA_DATA_CSV, csvFileName);
        List<String> targetColumnData = new ArrayList<>();

        InputStream inputStream = CSVReader.class.getClassLoader().getResourceAsStream(csvFilePath);
        if (inputStream == null) {
            throw new AutomationException("CSV file not found in the specified path");
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            int targetColumnIndex = 0;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    targetColumnIndex = getColumnIndexAndVerifyFilter(line, targetColumn, filterColumnName);
                    continue;
                }
                String[] columns = parseCSVLine(line);
                boolean filterPass = true;

                if (isFilterEnabled) {
                    filterPass = filterColumnIndex > -1 && filterColumnIndex < columns.length && columns[filterColumnIndex].equalsIgnoreCase(filterColumnValue);
                }

                if (filterPass) {
                    targetColumnData.add(columns[targetColumnIndex]);
                }
            }
            return targetColumnData;
        } catch (IOException e) {
            throw new AutomationException(e.getMessage(), e);
        }
    }

    /**
     * Obtiene el índice de la columna objetivo y verifica el filtro en la línea de encabezado del archivo CSV.
     *
     * @param headerLine      Línea de encabezado del archivo CSV.
     * @param targetColumn    Nombre de la columna objetivo.
     * @param filterColumnName Nombre de la columna de filtro.
     * @return Índice de la columna objetivo.
     * @throws AutomationException Si no se encuentra la columna especificada para la lectura de CSV.
     */
    private static int getColumnIndexAndVerifyFilter(String headerLine, String targetColumn, String filterColumnName) {
        String[] headers = parseCSVLine(headerLine);
        int targetColumnIndex = -1;
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(filterColumnName)) {
                isFilterEnabled = true;
                filterColumnIndex = i;
            }
            if (headers[i].equalsIgnoreCase(targetColumn)) {
                targetColumnIndex = i;
            }
        }
        if (targetColumnIndex == -1)
            throw new AutomationException("The indicated column was not found for CSV reading");
        return targetColumnIndex;
    }

    private static String[] parseCSVLine(String line) {
        return line.split(",");
    }
}