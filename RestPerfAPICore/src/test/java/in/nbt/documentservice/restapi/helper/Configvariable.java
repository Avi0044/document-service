package in.nbt.documentservice.restapi.helper;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.github.javafaker.Faker;

public class Configvariable extends in.nbt.core.restapi.helper.Configvariable {
    public static Faker faker = new Faker();

    @Override
    public String expandValue(String expression) {
        return super.expandValue(expression);
    }

    @Override
    public String getStringVar(String variable) {
        return super.getStringVar(variable);
    }

    @Override
    public void setStringVariable(String value, String variable) {
        super.setStringVariable(value, variable);
    }

    @Override
    public void assignValueToVar(String value, String variable) {
        super.assignValueToVar(value, variable);
    }

    @Override
    public String generateRandomNumber(String format) {
        return super.generateRandomNumber(format);
    }

    @Override
    public void setupEnvironmentProperties(String envName) {
        super.setupEnvironmentProperties(envName);
    }

    public static List<String> globalPropertyList = new ArrayList<>();

    public String genrateRandomName(){
        String Name = "";
        try {
            Name =faker.name().name();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Name;
    }
    public String getStringVarList() {
        String result = globalPropertyList.get(0);
        return result;
    }

    public void setStringVariable(String value) {
        if (globalPropertyList.contains(value)) {
            globalPropertyList.remove(value);
            globalPropertyList.add(value);
        } else {
            globalPropertyList.add(value);
        }
    }

}

