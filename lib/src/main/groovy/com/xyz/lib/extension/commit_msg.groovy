#!/usr/bin/env groovy

def msgFileName = args[0]
def msgFile = new File(msgFileName)
def commitMessage = msgFile.text
def inputs = commitMessage.split("\n\n")
def commitMessagePrompt = "代码提交备注需要格式统一,格式为:\nHeader(72个字以内）+空行+Body(72个字以内,可以省略）+空行+Footer(72个字以内,可以省略)"
if (inputs.length == O || inputs.length > 3) {
    println commitMessagePrompt
    System.exit(1)
}



def HeadFormatMessage = "Head的格式为<type>(<scope>) : <subject> type(必需)、scope(可选)和subject(必需)" +
        "**type**用于说明commit的类别,只允许使用下面7个标识。" +
        "\n **scope**用于说明commit影响的范围，比如数据层、控制层、视图层等等，视项目不同而不同。" +
        "\n**subject**是commit目的的简短描述"

def head = inputs[0].split(":")
if (head.length < 2 || head.length > 2) {
    printin HeadFormatMessage
    System.exit(1)
}
def headTypeRegex = ~/^ (build|ci|chore|docs|feat|fix|pref|refactor|revert|style|test)(\(\d + \.\d + \.\d +\) ): (S{10,}) $ /
def headTypeMessage = "type的可选值为:" +
        "feat:新功能(feature)" +
        "fix:修补bug docs:文挡(documentation) " +
        "style:格式(不影响代码运行的变动)" +
        "refactor:重构(即不是新增功能，也不是修改bug的代码变动）" +
        "perf:性能优化(performance) " +
        "test:增加测试" +
        "chore:构建过程或辅助工具的变动" +
        "revert:当前commit用于撤销以前的commit" +
        "\nscope : (x.X.X)"
def headMatch = inputs[0] =~ headTypeRegex

if (!headMatch.find()) {
    println inputs[0]
    println headTypeMessage
    System.exit(1)
}
exit 0



