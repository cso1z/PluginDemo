package com.xyz.lib;

import com.xyz.lib.extension.HooksExtension;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyTest implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        //1.添加扩展
        applyExtension(project);
        // project.afterEvaluate() 在解析build.gradle 之后
        //2. 检查groovy是否安装成功
        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                HooksExtension extension = (HooksExtension) project.getExtensions().getByName(HooksExtension.KEY);
                System.out.println(extension.rootPath + "  " + extension.commitMsg);

            }
        });
        System.out.println("hello world");
        //3. 拷贝 commitMsg至 .gt/hook/目录下
    }

    private void applyExtension(Project project) {
        project.getExtensions().add(HooksExtension.KEY, HooksExtension.class);
    }
}