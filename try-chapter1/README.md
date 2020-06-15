# 简介
```text
  诸多的类UNIX操作系统的涉及令人惊叹。即便是数十年后的今天，UNIX风格的操作系统架构仍是有史以来的最佳涉及之一。这种架构最重要的一个特征就是命令行界面或者
shell。shell环境使得用户能够于操作系统的内核进行交互操作。属于"脚本"更多涉及的是这类环境。编写脚本的通常使用某种基于解释器的编程语言。shell无非就是一些文件，
我们可以将一些 要执行的命令写入其中，然后通过shell来执行这些脚本.
```

**常见的shell类型:**
```text
    Linux系统下有多种Shell可以供我们选择，常见的有Bourne Again Shell (简称bash)、Bourne Shell（简称sh）、C-Shelll（简称csh）、
Korn Shell（简称ksh）.Bash（Bourner Again Shell）,它是目前大多数GUN/linux系统默认的shell环境。
```
**shell的编写规则**
```text
  shell脚本通常事一个以#!起始的文本文件，例如，#! /bin/bash,Linux环境下的任何脚本语言都是以这样的一个被称为shebang的特殊行作为
起始的。#!被至于解释器路径之前。/bin/bash是Bash的路径。
```

**shell脚本的运行的两种方式**
```text
1. 将脚本作为命令行的参数时的运行方式，此时脚本内的shebang行就没什么用处了。
例子： sh script.sh
2. 为了是shell脚本能够自己独立的运行，需要具备可执行的权限。需要脚本独立的运行，必须利用shebang行。它通过使用位于#！之后的解释前期来运行脚本。至于脚本的可执行全，
可以通过以下的方式设置：
    1. chmod a+x script.sh #给该脚本文件执行全。
    2. ./script.sh #./标识当前目录
    3. 使用脚本的的完整路径/home/path/script.sh #使用脚本的完整路径(shell程序会读取脚本的首航，查看shebang行是否为#! /bin/bash.它会是被/bin/bash,并在内部以如下的命令执行/bin/bash script.sh)

当我们打开一个终端的时候，该终端最初会执行一组命令来定义注入提示文本，颜色等各类的设置。这组命令位于用户home目录种的.bashrc脚本文件(~/.bashrc).Bash还维护了一个
历史记录文件~/.bash_history,用于保存用户运行过的命令。~是一种简写，代表了用户home目录的路径，在bash种每个名或者是命令序列是通过使用分号或者换行符来分隔的，比如
cmd1;cmd2 ==cmd1
            cmd2

```

## 終端打印

echo是終端打印的基本命令

```text
  默認情況下echo在每次調用后悔加一個換行符.
例子：
[sei@skyway-dev ~]$ echo "Welcome to Bash"
Welcome to Bash
使用单引号也可以
例子：
[sei@skyway-dev ~]$ echo 'Welcome to Bash'
Welcome to Bash
不使用引号也可以
[sei@skyway-dev ~]$ echo Welcome to Bash
Welcome to Bash

注意： 如果要打印!，就不要将其放入双引号中，或者你可以在其之上加上一个特殊的转义符号(\)将！转义，当然也可以在使用带双引号的文本是，你应该在echo之前使用set+H
以便能够正常的显示!.

三种打印的方式的问题:
1. 使用不带引号的echo时，你没法再索要显示的文本中使用，因为在bash shell中被用作命令界定符。
2. 以echo hello;hello为例，echo hello被视为一个命令，第二个hello则被视为另一个命令.
3. 使用带单引号的echo时，Bash不会对单引号的变量($var)求职，而只是照原样输出。

```

**printf**

```text
  printf使用引用文本或者有空格分隔的参数。我们可以在printf中使用格式化字符串。我们可以指定自渡船的宽度，左右对齐方式等。在默认的
情况下，printf并不想echo命令那样会自动添加换行符，我们必须在需要的时候动手天海，比如
prinft.sh
#!/bin/bash
printf "%-5s %-10s %-4s\n" No Name Mark
printf "%-5s %-10s %-4.2f\n" 1 Sarath 80.3456
printf "%-5s %-10s %-4.2f\n" 2 James 90.99989
printf "%-5s %-10s %-4.2f\n" 3 Jeff 77.564
格式化后输出的数据：
No    Name       Mark
1     Sarath     80.35
2     James      91.00
3     Jeff       77.56

解释:
    %-5s指明了一个格式为左对齐且宽度为5的字符串替代(-标识左对齐)。如果不用-指定对齐的方式，字符串则采用右对齐的方式。宽度指定了保留给
某个变量的字符数。对Name这一列指定了保留的宽度是10.因此，任何Name字段都会被显示的在10字符的宽度保留区域内，如果内容不足10字符，余下的
则以空格字符填充。对于浮点数，我们可以参对小数部分进行舍入，例如%-4.2f，其中.2指定了保留的2个小数。但是需要注意的是，没行后面都需要加入
一个换行符\n.

补充：
   echo和printf种的标志应该出现在命令行内任何字符串之前，否则Bash会将其试为另外一个字符串。
```



## 变量和环境变量

```text
  变量是任何一种编程语言必不可少的组成部分，用于存放各类数据。脚本语言通常不需要在使用变量之前生命其类型。值需要在直接赋值就可以了
在Bash中，没一个环境变量的值都是字符串。无论你给变量赋值时又没有使用引号，值都会以字符串的形式存储。有一些特殊的变量会被shell环境
和操作系统环境来存储这些特别的值。这类变量就被称为环境变量。

```




























