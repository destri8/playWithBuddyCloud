package sample.com.buddyclouddroidtest.retrofit;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

/**
 * Created by Destri on 5/18/15.
 */
public class StringConverter extends GsonConverter {

    public StringConverter(Gson gson) {
        super(gson);
    }

    @Override
    public Object fromBody(TypedInput typedInput, Type type) throws ConversionException {
        String text = null;
        try {
            text = fromStream(typedInput.in());
        } catch (IOException ignored) {/*NOP*/ }

        return text;
    }


//    @Override
//    public Object fromBody(TypedInput body, Type type) throws ConversionException {
//        System.out.println("frombody");
//        return super.fromBody(body, type);
//    }

    @Override
    public TypedOutput toBody(Object object) {
        System.out.println("tobody");
        return super.toBody(object);
    }

    public static String fromStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}
