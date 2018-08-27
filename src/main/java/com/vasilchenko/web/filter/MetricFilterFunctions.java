package com.vasilchenko.web.filter;

import com.vasilchenko.web.metric.MetricClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс обработки фильтров пользователя
 *
 * @version 1.0
 * @autor Viacheslav Vasilchenko
 */
public class MetricFilterFunctions {

    /**
     * Фильтр пути  URI
     *
     * @return отфильтрованый список
     * @Param currentList - список текущих реквестов
     * @Param filterURIPath - пути, для фильтрации вывода
     */
    public static List<MetricClass> UrlFilter(List<MetricClass> currentList, String filterURIPath) {
        List<String> urlPathList = new ArrayList<>();

        for (String s : filterURIPath.split(",")) urlPathList.add(s.trim());

        List<MetricClass> temp = new ArrayList<>(currentList);
        for (MetricClass var : currentList) {
            if (!urlPathList.contains(var.getUri()))
                temp.remove(var);
        }
        return temp;
    }

    /**
     * Фильтр особенноого слова в реквесте
     *
     * @return отфильтрованый список
     * @Param currentList - список текущих реквестов
     * @Param filterURIDataByWord - строка поиска в реквесте
     */
    public static List<MetricClass> ResponseWordFilter(List<MetricClass> currentList, String filterURIDataByWord) {
        List<MetricClass> temp = new ArrayList<>(currentList);
        for (MetricClass var : currentList) {
            if (!var.getResponseBody().contains(filterURIDataByWord))
                temp.remove(var);
        }
        return temp;
    }

    /**
     * Фильтр изменение валидности времени загрузки
     *
     * @return отфильтрованый список
     * @See MetricClass
     * @Param currentList - список текущих реквестов
     * @Param filterURIResponseTime - время, установленое пользователем
     */
    public static List<MetricClass> ResponseTimeFilter(List<MetricClass> currentList, long filterURIResponseTime) {
        List<MetricClass> temp = new ArrayList<>(currentList);
        for (MetricClass var : temp) {
            if (var.getTime() > filterURIResponseTime)
                var.setValidation(false);
            else
                var.setValidation(true);
        }
        return temp;
    }

    public static long parseLong(String value, long standardValue) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            return standardValue;
        }
    }
}
