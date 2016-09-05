package com.zwc.inject.processor;

import com.zwc.inject.annotation.BindView;
import com.zwc.inject.annotation.BindViews;
import com.zwc.inject.annotation.OnClick;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * 用来生成内部类的处理器
 */
@SupportedAnnotationTypes({"com.zwc.inject.annotation.BindView", "com.zwc.inject.annotation.BindViews", "com.zwc.inject.annotation.Onclick"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class BindViewProcessor extends AbstractProcessor {
    public static final String SUFFIX = "$ViewBinder";
    private Messager messager;
    private Map<String, List<VariableElement>> mElementMap = new HashMap<>();//用来存储注解元素的map

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //寻找BindView注解,添加到map中
        findBindViewAnno(roundEnv);
        //寻找BindViews注解,添加到map中
        findBindViewsAnno(roundEnv);
        //寻找OnClick注解,添加到map中
        findOnClickAnno(roundEnv);

        //进行类的生成工作
        generate(mElementMap);
        return true;
    }


    private void findBindViewAnno(RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(BindView.class)) {
            if (element == null || !(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            String className = element.getEnclosingElement().getSimpleName().toString();
            List<VariableElement> variableElementList = mElementMap.get(className);
            if (variableElementList == null) {
                variableElementList = new ArrayList<>();
                mElementMap.put(className, variableElementList);
            }
            variableElementList.add(variableElement);
        }
    }

    private void findBindViewsAnno(RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(BindViews.class)) {
            if (element == null || !(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            String className = element.getEnclosingElement().getSimpleName().toString();
            List<VariableElement> variableElementList = mElementMap.get(className);
            if (variableElementList == null) {
                variableElementList = new ArrayList<>();
                mElementMap.put(className, variableElementList);
            }
            variableElementList.add(variableElement);
        }
    }

    private void findOnClickAnno(RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(OnClick.class)) {
            if (element == null || !(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            String className = element.getEnclosingElement().getSimpleName().toString();
            List<VariableElement> variableElementList = mElementMap.get(className);
            if (variableElementList == null) {
                variableElementList = new ArrayList<>();
                mElementMap.put(className, variableElementList);
            }
            variableElementList.add(variableElement);
        }
    }

//生成内部类的方法

    private void generate(Map<String, List<VariableElement>> map) {
        if (null == map || map.size() == 0) {
            return;
        }
        for (String className : map.keySet()) {
            List<VariableElement> variableElementList = map.get(className);
            if (variableElementList == null || variableElementList.size() <= 0) {
                continue;
            }
            //生成package 和 import的类
            String packageName = variableElementList.get(0).getEnclosingElement().getEnclosingElement().toString();
            StringBuilder builder = new StringBuilder()
                    .append("package ").append(packageName).append(";\n\n")
                    .append("import com.zwc.inject.provider.Provider; \n")
                    .append("public class ").append(className).append(SUFFIX).append(" implements com.zwc.inject.IBind ").append("{\n") // open class
                    .append("    public void inject(Object host, Object object, Provider provider) {\n")
                    .append("        ").append(className).append(" activity = (").append(className).append(")host;\n");
            for (VariableElement variableElement : variableElementList) {
                BindView bindView = variableElement.getAnnotation(BindView.class);
                BindViews bindViews = variableElement.getAnnotation(BindViews.class);
                OnClick onClick = variableElement.getAnnotation(OnClick.class);

                if (bindView != null) {
                    //生成bindView注解相关代码
                    log(bindView.toString());
                    builder.append("        activity.").append(variableElement.getSimpleName().toString()).append("=(").append(variableElement.asType()).append(")provider.findView(").append("object,").append(bindView.value()).append(");\n");
                } else if (bindViews != null) {
                    //生成bindViews注解相关代码
                    log(bindViews.toString());
                    int[] value = bindViews.value();
                    builder.append("        activity.").append(variableElement.getSimpleName().toString()).append("=").append("new ").append(variableElement.asType().toString().replace("[]","")).append("[").append(value.length).append("];\n");
                    for(int i=0;i<value.length;i++){
                        builder.append("        activity.").append(variableElement.getSimpleName().toString()).append("[").append(i).append("]").append("=(").append(variableElement.asType().toString().replace("[]","")).append(")provider.findView(").append("object,").append(value[i]).append(");\n");
                    }
                } else if (onClick != null) {
                    //生成OnClick注解相关代码
                    log(onClick.toString());
                }
            }
            builder.append("    }\n}\n");
            // write the file
            try {
                String bindViewClassName = packageName + "." + className + SUFFIX;
                JavaFileObject source = processingEnv.getFiler().createSourceFile(bindViewClassName);
                Writer writer = source.openWriter();
                writer.write(builder.toString());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                log(e.getMessage());
            }
        }
    }

    private void log(String msg) {
        messager.printMessage(Diagnostic.Kind.WARNING, msg);
    }

}
