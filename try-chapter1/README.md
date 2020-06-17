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

```text
  变量采用常见的命名方式进行命名。当一个应用程序执行的时候，它接收一组环境变量。可以使用env命令终端中查看所有于此终端进程相关的环境
变量。对于每个进程，在其运行时的环境变量可以使用下面的命令查看：
cat /proc/$PID/environ
例子：
#cat /proc/25024/environ
XDG_SESSION_ID=27158HOSTNAME=xxTERM=xtermSHELL=/bin/bashHISTSIZE=1000SSH_CLIENT=183.14.31.11 29510 
22SSH_TTY=/dev/pts/1USER=rootLS_COLORS=rs=0:di=01;34:ln=01;36:mh=00:pi=40;33:so=01;35:do=01;35:bd=40;33;01:cd=40;33;01:or=40;
....
```


```text
  一个变量的赋值可以是var=value，var是变量的名字，value是赋值给变量，如果value不包含任何空白字符(例如空格)，那么她不需要使用使用引号进行引用，
反之，则必须要使用单引号或者双引号。

```
**环境变量的设置**
```text
  export命令用来设置环境变量，至此之后，从当前shell脚本执行的任何程序都会集成这个环境变量。我们可以按照自己的需要
在执行的应用程序或者shell脚本中导出特定的变量。在默认情况下，很多标准的环境变量可供shell使用。
例子：
echo $PATH
/usr/local/mongodb/mongodb-linux-x86_64-4.0.16/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/usr/jdk/jdk1.8.0_211//bin:/usr/jdk/jdk1.8.0_211//bin:/root/bin
如果我们需要在PATH中添加一条新的路径，可以使用
export PATH=$PATH:/home/user/bin=====PATH="$PATH:/home/user/bin"
                                     export PATH
获取字符串的长度：
[root@xx shell]# var='123782481029391203'
[root@xx shell]# echo ${#var}
18

识别当前shell的版本
[root@xx shell]# echo $SHELL
/bin/bash

```
算数操作
```text
运算操作
#!/bin/bash
no1=4;
no2=5
#let命令可以直接执行基本的算数操作
let result=no1+no2
echo $result
let no1++
let no2++
let result=no1+no2
echo $result
# 操作符[]的使用方法和let命令类似
echo "操作符[]的使用方法和let命令类似";
result=$[no1+no2]
echo $result
# (())运算符号也可以实现算数运算
echo "(())运算符号也可以实现算数运算";
result=$((no1+no2))
echo $result
# expr同样可以用于基本的算数运算
echo 'expr 11+8'
result=$(expr $result + 8)
echo $result
# bc是一个高级算数运算的工具，这个精密的计算器包含了大量的选项，我们可以借助它执行浮点数的运算
echo "bc是一个高级算数运算的工具，这个精密的计算器包含了大量的选项，我们可以借助它执行浮点数的运算";
echo "4*0.25" | bc
# 其他参数可以至于要执行的具体操作之前，同时以分号作为界定符，通过stdin传递给bc
#设定小数精度(数值方位)；
echo "scale=2;3/8" | bc
#进制转换
echo "obase=2;$result" | bc
echo "obase=10;$result" | bc
# 计算平方以及平方根
echo "sqrt(100)" | bc
echo "10^10" | bc
```


## 文件描述符和重定向
```text
  文件描述符于文件输入，输出相关联的整数。它们用来跟踪已经打开的文件，最常见的文件描述父是stdin,stdout
和stderr。我们可以将某个文件描述符的内容重定向到另一个文件描述符中。
   文件描述符是与一个打开的文件或者数据流相关的联的整数。文件描述符0，1以及2是系统预留的。
0----stdin(标准输入)
1----stdout(标准输出)
2-----stderr(标准错误)  
```

**实践**

```text

1. 使用>文件重定向(该文件重定向操作符需要先清空文件，然后在将内容输入)
例子：
[root@xx shell]# echo "this is a demo" 1>temp.txt 
[root@xx shell]# cat temp.txt 
this is a demo

2.  使用>>文件重定向操作符(该操符可以将新的内容追加到文件)
例子：
[root@xx shell]# echo "this is a demo2" 1>>temp.txt 
[root@xx shell]# cat temp.txt 
this is a demo
this is a demo2


3. 错误输出
例子：
[root@xx shell]# ls +
ls: cannot access +: No such file or directory
[root@xx shell]# ls + 2>>error.txt 
[root@xx shell]# cat error.txt 
ls: cannot access +: No such file or directory
ls: cannot access +: No such file or directory

4. 将stderr单独重定向到一个文件，将stdout重定向到另一个文件
例子：
ls + 2>stderr.txt 1>stdout.txt
5.将stderr转换成stdout，使得stderr和stdout都被重定向到同一个文件中：
cmd 2>&1 stdout.txt<<====>> cmd &>stdout.txt
5. 文件输出重定位到/dev/null，这个/dev/null是一个特殊的设备文件，这个文件接收到的任何数据都会被丢弃。因此，
null设备通常被称为位桶(bit bucket)或黑洞。 
 
6. tee命令可以一方面将数据重定向到文件，另一方面还可以提供一份重定向的数据的副本作为后续命令的stdin.
[root@xx shell]# ls / | tee -a temp.txt |cat -n ## -a选项可以使得内容追加
     1	bin
     2	boot
     3	chendom
     4	data
     5	dev
     6	dump.rdb
     7	etc
     8	home
     9	lib
    10	lib64
    11	lost+found
    12	media
    13	mnt
    14	opt
    15	proc
    16	root
    17	run
    18	sbin
    19	srv
    20	sys
    21	tmp
    22	tmp10cdcbd3-dc25-4339-8d8e-6526dfe77edb.jpg
    23	tmpd0d92061-9796-402b-ab65-2bd46b783cd8.jpg
    24	usr
    25	var

```






























