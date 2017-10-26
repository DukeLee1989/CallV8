package com.eclipsesource.callv8;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.eclipsesource.v8.V8;
import com.eclipsesource.v8.V8Object;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        V8 runtime = V8.createV8Runtime();

        NativeConsole4Js nativeConsole = new NativeConsole4Js();
        V8Object v8Console = new V8Object(runtime);
        runtime.add("console", v8Console);

        v8Console.registerJavaMethod(nativeConsole, "jia", "jia", new Class[]{int.class, int.class});
        v8Console.registerJavaMethod(nativeConsole, "print", "print", new Class[]{String.class});

        StringBuilder sb = new StringBuilder();
        sb.append("var str= 'Hello' + console.jia(100, 111);");
        sb.append("console.print(str);");
        runtime.executeVoidScript(sb.toString());


        v8Console.release();
        runtime.release();
    }
}
