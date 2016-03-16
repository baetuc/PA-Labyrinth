package Controller;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Cip on 13-Mar-16.
 */
public class CustomInputStreamReader extends InputStreamReader {

    public CustomInputStreamReader(InputStream stream) {
        super(stream);
    }

    @Override
    public void close() {
        // nothing to do here
    }

}
