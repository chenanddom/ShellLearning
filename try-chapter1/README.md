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

**重定向文件补充**
```text
1. 将文件重定向到命令
$cmd < file
2. 冲定向脚本内部的文本块（需要项标准输入一样进行重定向）
例子(将脚本内EOF之前的内容定向到log.txt)
#!/bin/bash
cat <<EOF>log.txt
LOG FILE HEADER
This is a test log file
Function:System statistics
EOF



```

[重定向脚本内部的文本块](files/redirectionFile.sh)

## 数组
```text
  Bash同时支持普通数组和关联数组，普通数组只能使用整数作为数组索引，而关联数组可以使用字符串作为数组索引
关联数组在很多操作当中相当有用，Bash从4.0版本开始支持关联数组。

[root@xx shell]# array_var=(1 2 3 4 5 6)
[root@xx shell]# echo ${array_var[0]}
1
[root@xx shell]# index=3
[root@xx shell]# echo ${array_var[${index}]}}
4}
[root@xx shell]# echo ${array_var[${index}]}
4
[root@xx shell]# echo ${array_var[${index}]}
4
[root@xx shell]# echo ${array_var[${index}]}
4
[root@xx shell]# echo ${array_var[${index}]}
4
[root@xx shell]# echo ${array_var[*]}
1 2 3 4 5 6

关联数组
和普通数组的区别是关联数组可以使用任意的文本作为数组的索引。
首先需要使用单独的声明语句将一个变量生命位关联数组
declare -A ass_array
声明之后可以使用两种方法将元素添加到关联数组中
1.利用内嵌索引-值的列表法：
ass_rray=([index1]=value1 [index2]=value2)
2. 使用独立的索引-值进行赋值：
ass_array[index1]=value1
ass_array[index2]=value2
例子：
[root@xx shell]# declare -A ass_array
[root@xx shell]# ass_array=([index1]=values [index2]=value2)
[root@xx shell]# echo ${!ass_array[*]}
index1 index2
[root@xx shell]# echo ${!ass_array[*]}
index1 index2
[root@xx shell]# echo ${ass_array[*]}
values value2
[root@xx shell]# echo ${!ass_array[@]}
index1 index2
[root@xx shell]# echo ${!ass_array[@]}
index1 index2

```

## 日期

```text
  使用date命令可以的一个UTC，又称为世界标准时间或者世界协调时间
[root@xx ~]# date
Thu Jun 18 08:56:36 CST 2020
使用date +%s可以得到1970-1月-1日0时0分0秒起至当前时刻的总秒数，--date用于提供日期串作为输入。但是我们
可以使用任意的日期格式化选项来打印输出。将日期串作为输入的能够用来获取给定日期的日期时星期几。
[root@xx ~]# date --date 'Thu Jun 18 08:56:36 CST 2020' +%s
1592441796

```


**日期的格式**
```text
% Y 年（例如：1970，2018等） 

% m 月（01..12）

% d 一个月的第几天（01..31）

% H 小时（00..23）

% M 分（00..59）

% S 秒（00..59）
```

[计算程序执行所耗掉的时长](files/myDate.sh)


## 调试脚本
```shell script
使用选项-x，启动调式shell脚本
bash -x script.sh
-x标识将脚本中执行过的每一行都输出到stdout.不过，我们可以要求之关注脚本某些部分的命令参数的打印输出。
* set -x:在执行的时候显示参数和命令
* set +X:禁止调试
* set -V: 当命令进行读取时显示输入
* set +v:禁止打印输入
例子：
#!/bin/bash
function DEBUG()
{
# $@表示获取所有参数，":"告诉shell不要进行任何操作
[ "$_DEBUG" == "on" ] && $@ || :
}
for i in {1..10}
do
DEBUG echo $i
done

执行结果:
[root@xx shell]# _DEBUG="on" sh debugScript.sh 
1
2
3
4
5
6
7
8
9
10

注意：
:(){ :|:& };:这是一个fork炸弹
```

## 定义函数
```shell script
Bash也可以像其他语言一样实现函数的定义
例如
[root@xx shell]# fname() { echo $1,$2; }
[root@xx shell]# fname parameter1 parameter2
parameter1,parameter2
通过上面的例子我们可以知道可以使用函数名直接调用，在后面添加参数(参数之间使用空格隔开即可)
* $1 是第一个参数
* $2是第二个参数
* $n是第n个参数
* $@ 是获取所有的参数的列表
* $* 将获取到的所有的参数当作一个字符串

```

**导出函数**
```shell script
shell中的函数可以像环境变量一样使用export导出，如此依赖，函数的作用域就可以扩展到子进程中
#定义函数
fname(){echo $1,$2}
#导出函数
export -f fname
```

[导入函数](files/exportFunction.sh)

**读取命令返回值**
```shell script
可以按照下面的方式获取命令或者函数的返回值：
cmd;
echo $?;
$?会给出命令cmd的返回值。
返回值被称为退出状态。它可以用于分析执行成功与否。如果命令成功退出，那么退出状态为0，否则为非0.
例子
#!/bin/bash
CMD="ls /"
status
$CMD
if [ $? -eq 0 ];
then 
echo "$CMD executed successfully"
else
echo "$CMD terminated unsuccessfully"
fi 
运行结果:
[root@xx shell]# ./success_test.sh 
bin  boot  chendom  data  dev  dump.rdb  etc  home  lib  lib64	lost+found  media  mnt	opt  proc  root  run  sbin  srv  sys  tmp  tmp10cdcbd3-dc25-4339-8d8e-6526dfe77edb.jpg	tmpd0d92061-9796-402b-ab65-2bd46b783cd8.jpg  usr  var
ls / executed successfully

```


## 读取命令序列的输出
```text
  Shell脚本最好的额地方就是可以轻松的将多个命令或者工具组合起来生成输出。一个命令的输出可以作为另一个命令的输入，而
这个命令的输出又会称为另一个命令的输入。这种命令组合的输出可以存储在一个变量当中。
  输入通常是通过stdin或参数传递给命令。输出要么出现在stderr,要么出现在stdout。当我们组合多个命令时，同时将stdin用于
输入，stdout用于输出。 这些命令被称为过滤器(filter)。我们使用管道(pipe)来连接每一个过滤器。管道操作符是"|".
例如$ cmd1 | cmd2 | cmd3 cmd1的输出传递给cmd2.而cmd2的输出传递给cmd3.

[root@xx shell]# ls | cat -n > out
[root@xx shell]# cat out.txt 
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

**利用子shell生成独立的进程**
```text
  子shell本身就是独立的进程。可以使用()操作符来定义一个子shell。
例子：
#!/bin/bash
pwd
(cd /bin; ls)
pwd
执行结果:

[root@xx shell]# ./subShell.sh 
/usr/shell
[		    cpio		   flock		  httxt2dbm		lsns		    nl-classid-lookup	  pkill			    renice		   split			   umount
a2p		    cpp			   fmt			  i386			lsscsi		    nl-class-list	  pkla-admin-identities     repoclosure		   sprof			   unalias
ab		    cpupower		   fold			  iconv			lua		    nl-cls-add		  pkla-check-authorization  repodiff		   sqlite3			   uname
addr2line	    crlutil		   free			  id			luac		    nl-cls-delete	  pkttyagent		    repo-graph		   ssh				   unexpand
alias		    crontab		   g++			  idiag-socket-details	m4		    nl-cls-list		  pl2pm			    repomanage		   ssh-add			   unicode_start
apropos		    csplit		   gapplication		  idn			machinectl	    nl-fib-lookup	  pldd			    repoquery		   ssh-agent			   unicode_stop
ar		    csslint-0.6		   gawk			  igawk			mail		    nl-link-enslave	  plymouth		    repo-rss		   ssh-copy-id			   uniq
arch		    ctr			   gcc			  info			Mail		    nl-link-ifindex2name  pmap			    reposync		   ssh-keygen			   unlink
as		    curl		   gcc-ar		  infocmp		mailq		    nl-link-list	  pod2html		    repotrack		   ssh-keyscan			   unshare
aserver		    cut			   gcc-nm		  infokey		mailq.postfix	    nl-link-name2ifindex  pod2man		    reset		   ssltap			   unxz
at		    date		   gcc-ranlib		  infotocap		mailx		    nl-link-release	  pod2text		    resizecons		   stat				   update-ca-trust
atq		    db_archive		   gcov			  install		make		    nl-link-set		  pod2usage		    rev			   stdbuf			   update-mime-database
atrm		    db_checkpoint	   gdbus		  install_compass	makedb		    nl-link-stats	  post-grohtml		    rm			   strace			   uptime
audit2allow	    db_deadlock		   gencat		  ionice		man		    nl-list-caches	  powernow-k8-decode	    rmail		   strace-log-merge		   urlgrabber
audit2why	    db_dump		   genl-ctrl-list	  iostat		mandb		    nl-list-sockets	  pr			    rmail.postfix	   strings			   users
aulast		    db_dump185		   geoiplookup		  ipcalc		manpath		    nl-monitor		  preconv		    rmdir		   strip			   usleep
aulastlog	    db_hotbackup	   geoiplookup6		  ipcmk			mapscrn		    nl-neigh-add	  pre-grohtml		    rpcgen		   stty				   usx2yloader
ausyscall	    dbilogstrip		   geoipupdate		  ipcrm			markdown_py	    nl-neigh-delete	  printenv		    rpm			   su				   utmpdump
auvirt		    dbiprof		   geqn			  ipcs			mcookie		    nl-neigh-list	  printf		    rpm2cpio		   sudo				   uuclient
awk		    dbiproxy		   getconf		  iptables-xml		md5sum		    nl-neightbl-list	  prlimit		    rpmdb		   sudoedit			   uuidgen
base64		    db_load		   getent		  isosize		mesg		    nl-pktloc-lookup	  prtstat		    rpmkeys		   sudoreplay			   vdir
basename	    db_log_verify	   getfacl		  jobs			mixartloader	    nl-qdisc-add	  ps			    rpmquery		   sum				   verifytree
bash		    db_printlog		   getkeycodes		  join			mkdir		    nl-qdisc-delete	  psed			    rpmverify		   sx				   vi
bashbug		    db_recover		   getopt		  journalctl		mkfifo		    nl-qdisc-list	  psfaddtable		    rsyslog-recover-qi.pl  sync				   view
bashbug-64	    db_replicate	   getopts		  jsondiff		mkinitrd	    nl-route-add	  psfgettable		    runc		   systemctl			   vim
batch		    db_stat		   gettext		  jsonpatch		mknod		    nl-route-delete	  psfstriptable		    runcon		   systemd-analyze		   vimdiff
bc		    db_tuner		   gettext.sh		  jsonpointer		mktemp		    nl-route-get	  psfxtable		    run-parts		   systemd-ask-password		   vimtutor
bg		    db_upgrade		   gio			  kbdinfo		modutil		    nl-route-list	  pstree		    rvi			   systemd-cat			   vlock
bond2team	    dbus-binding-tool	   gio-querymodules-64	  kbd_mode		mongo		    nl-rule-list	  pstree.x11		    rview		   systemd-cgls			   vmstat
bootctl		    dbus-cleanup-sockets   glib-compile-schemas   kbdrate		mongod		    nl-tctree-list	  pstruct		    rvim		   systemd-cgtop		   vxloader
bsondump	    dbus-daemon		   gmake		  kdumpctl		mongodump	    nl-util-addr	  pt-align		    rx			   systemd-coredumpctl		   w
busctl		    dbus-monitor	   gneqn		  kernel-install	mongoexport	    nm			  pt-archiver		    rz			   systemd-delta		   wait
c++		    dbus-send		   gnroff		  kill			mongofiles	    nmcli		  ptaskset		    s2p			   systemd-detect-virt		   wall
c2ph		    dbus-uuidgen	   gpasswd		  killall		mongoimport	    nm-online		  pt-config-diff	    sadf		   systemd-escape		   watch
c89		    db_verify		   gpg			  kmod			mongorestore	    nmtui		  pt-deadlock-logger	    sandbox		   systemd-firstboot		   watchgnupg
c99		    dc			   gpg2			  krb5-config		mongos		    nmtui-connect	  pt-diskstats		    sar			   systemd-hwdb			   wc
cal		    dd			   gpg-agent		  last			mongostat	    nmtui-edit		  pt-duplicate-key-checker  sb			   systemd-inhibit		   wdctl
ca-legacy	    deallocvt		   gpgconf		  lastb			mongotop	    nmtui-hostname	  pt-fifo-split		    scp			   systemd-loginctl		   wget
cancel		    debuginfo-install	   gpg-connect-agent	  lastlog		more		    nohup		  pt-find		    script		   systemd-machine-id-setup	   whatis
cancel.cups	    df			   gpg-error		  lchfn			mount		    nproc		  pt-fingerprint	    scriptreplay	   systemd-notify		   whereis
captoinfo	    dgawk		   gpgparsemail		  lchsh			mountpoint	    nroff		  pt-fk-error-logger	    sdiff		   systemd-nspawn		   which
cat		    diff		   gpgsplit		  ld			mpstat		    nsenter		  pt-heartbeat		    secon		   systemd-path			   whiptail
catchsegv	    diff3		   gpgv			  ld.bfd		msgattrib	    ntpstat		  pt-index-usage	    sed			   systemd-run			   who
catman		    dir			   gpgv2		  ldd			msgcat		    numfmt		  pt-ioprofile		    sedismod		   systemd-stdio-bridge		   whoami
cc		    dircolors		   gpg-zip		  ld.gold		msgcmp		    objcopy		  pt-kill		    sedispol		   systemd-sysv-convert		   write
cd		    dirname		   gpic			  less			msgcomm		    objdump		  pt-mext		    semodule_package	   systemd-tmpfiles		   x86_64
centrino-decode     dmesg		   gprof		  lessecho		msgconv		    od			  pt-mongodb-query-digest   seq			   systemd-tty-ask-password-agent  x86_64-redhat-linux-c++
certutil	    dnsdomainname	   grep			  lesskey		msgen		    oldfind		  pt-mongodb-summary	    setarch		   sz				   x86_64-redhat-linux-g++
c++filt		    docker		   groff		  lesspipe.sh		msgexec		    open		  pt-mysql-summary	    setfacl		   tabs				   x86_64-redhat-linux-gcc
chacl		    docker-compose	   grops		  lexgrog		msgfilter	    openssl		  pt-online-schema-change   setfont		   tac				   x86_energy_perf_policy
chage		    dockerd		   grotty		  link			msgfmt		    openvt		  pt-pg-summary		    setkeycodes		   tail				   xargs
chardetect	    docker-init		   groups		  linux32		msggrep		    os-prober		  pt-pmp		    setleds		   tailf			   xgettext
chattr		    docker-proxy	   grub2-editenv	  linux64		msghack		    p11-kit		  pt-query-digest	    setmetamode		   tapestat			   xmlcatalog
chcat		    domainname		   grub2-file		  linux-boot-prober	msginit		    package-cleanup	  pt-secure-collect	    setpriv		   tar				   xmllint
chcon		    dracut		   grub2-fstest		  ln			msgmerge	    passwd		  pt-show-grants	    setsid		   taskset			   xmlwf
checkmodule	    du			   grub2-glue-efi	  loadkeys		msgunfmt	    paste		  pt-sift		    setterm		   tbl				   xxd
checkpolicy	    dumpkeys		   grub2-kbdcomp	  loadunimap		msguniq		    patch		  pt-slave-delay	    setup-nsssysinit	   teamd			   xz
cheetah		    dwp			   grub2-menulst2cfg	  locale		mv		    pathchk		  pt-slave-find		    setup-nsssysinit.sh    teamdctl			   xzcat
cheetah-analyze     easy_install	   grub2-mkfont		  localectl		nail		    pax			  pt-slave-restart	    setvtrgb		   teamnl			   xzcmp
cheetah-compile     easy_install-2.7	   grub2-mkimage	  localedef		namei		    pchrt		  pt-stalk		    sftp		   tee				   xzdec
chfn		    echo		   grub2-mklayout	  logger		nc		    pcre-config		  pt-summary		    sg			   test				   xzdiff
chgrp		    ed			   grub2-mknetdir	  login			ncat		    peekfd		  pt-table-checksum	    sh			   testgdbm			   xzegrep
chmod		    egrep		   grub2-mkpasswd-pbkdf2  loginctl		ndptool		    percona-release	  pt-table-sync		    sha1sum		   tic				   xzfgrep
chown		    eject		   grub2-mkrelpath	  logname		needs-restarting    perl		  pt-table-usage	    sha224sum		   time				   xzgrep
chronyc		    elfedit		   grub2-mkrescue	  logresolve		neqn		    perl5.16.3		  pt-upgrade		    sha256sum		   timedatectl			   xzless
chrt		    env			   grub2-mkstandalone	  look			netstat		    perlbug		  pt-variable-advisor	    sha384sum		   timeout			   xzmore
chsh		    envsubst		   grub2-script-check	  lp			newaliases	    perldoc		  pt-visual-explain	    sha512sum		   tload			   yes
chvt		    eqn			   grub2-syslinux2cfg	  lp.cups		newaliases.postfix  perlthanks		  ptx			    show-changed-rco	   tmon				   ypdomainname
cifsiostat	    ex			   gsettings		  lpoptions		newgrp		    pflags		  pwd			    showconsolefont	   toe				   yum
cksum		    expand		   gsoelim		  lppasswd		nf-ct-add	    pgawk		  pwdx			    show-installed	   top				   yum-builddep
clear		    expr		   gss-client		  lpq			nf-ct-list	    pgrep		  pwmake		    showkey		   touch			   yum-config-manager
cloud-init	    factor		   gtar			  lpq.cups		nf-exp-add	    pic			  pwscore		    shred		   tput				   yum-debug-dump
cloud-init-per	    fallocate		   gtbl			  lpr			nf-exp-delete	    piconv		  pydoc			    shuf		   tr				   yum-debug-restore
cloud-init-upgrade  false		   gtroff		  lpr.cups		nf-exp-list	    pidstat		  python		    signtool		   tracepath			   yumdownloader
cmp		    fc			   gunzip		  lprm			nf-log		    pinentry		  python2		    signver		   tracepath6			   yum-groups-manager
cmsutil		    fg			   gzexe		  lprm.cups		nf-monitor	    pinentry-curses	  python2.7		    sim_client		   troff			   zcat
col		    fgconsole		   gzip			  lpstat		nf-queue	    ping		  ranlib		    size		   true				   zcmp
colcrt		    fgrep		   h2ph			  lpstat.cups		nfsiostat-sysstat   ping6		  raw			    skill		   truncate			   zdiff
colrm		    file		   hdsploader		  ls			ngettext	    pinky		  rb			    slabtop		   trust			   zegrep
column		    find		   head			  lsattr		nice		    pip			  read			    sleep		   tset				   zfgrep
comm		    find2perl		   hexdump		  lsblk			nisdomainname	    pip2		  readelf		    slogin		   tsort			   zforce
command		    findmnt		   hostid		  lsb_release		nl		    pip2.7		  readlink		    snice		   tty				   zgrep
compile_et	    find-repos-of-install  hostname		  lscpu			nl-addr-add	    pk12util		  realpath		    soelim		   turbostat			   zipdetails
containerd	    fipscheck		   hostnamectl		  lsinitrd		nl-addr-delete	    pkaction		  recode-sr-latin	    sort		   tzselect			   zless
containerd-shim     fipshmac		   htdbm		  lsipc			nl-addr-list	    pkcheck		  red			    sotruss		   udevadm			   zmore
coredumpctl	    firewall-cmd	   htdigest		  lslocks		nl-class-add	    pkexec		  redhat_lsb_init	    spax		   ul				   znew
cp		    firewall-offline-cmd   htpasswd		  lslogins		nl-class-delete     pkg-config		  rename		    splain		   umask			   zsoelim
/usr/shell

通过上面的执行结果我们可以知道，命令在子shell中执行时，不会对当前shell有任何的影响，所有的改变仅限于子shell内。例如
```


**通过引用子shell的方式保存空格和换行符**

```text
使用子shell或者反引用的方法将命令的输出读入一个变量当中，可以将它放入双引号中，以保留空格和换行符(\n).
例子：
[root@xx shell]# out=$(cat test.txt)
[root@xx shell]# echo $out
1 2 3 #换行符和空格丢失
为了保留换行符或者空格，可以使用将输出加上双引号
[root@xx shell]# out="$(cat test.txt)"
[root@xx shell]# echo "$out"
1

2

3
```

**以不按回车键的方式读取字符"n"**
```text
  read是一个重要的Bash命令，用于从键盘或者标准输入中读取文本。我们可以使用read以交互的形式来读取来自用户的输入。
例子：
#限制输入的字符串
[root@xx shell]# read -n 3 var
abc[root@xx shell]# echo $var
abc
# 显示提示信息
[root@xx shell]# read -p "Please enter:" var
Please enter:adbcfrrfradasda
[root@xx shell]# echo $var
adbcfrrfradasda
# 添加输入时效
[root@xx shell]# read -t 2 var
[root@xx shell]# read -d ":" var
asdhajdhsadasd:[root@xx shell]# echo $var
asdhajdhsadasd
[root@xx shell]# 

```

## 字段分隔符和迭代器
```text
  内部字段分隔符(Internal Field Separator,简称IFS)是shell脚本中的一个重要的概念。在处理文本时，它是相当有用的。
IFS默认之位空白字符串(换行符，指标符或者空格)。



例子：
#!/bin/bash
data="name,sex,rollno,location"
oldIFS=$IFS
IFS=,NOEW,
for item in $data;
do
echo Item: $item
done
IFS=$oldIFS
执行结果:
[root@xx shell]# ./ifsTest.sh 
Item: name
Item: sex
Item: rollno
Item: location

例子2：
#!/bin/bash
line="root:x:0:0:root:/root:/bin/bash"
oldIFS=$IFS;
IFS=":"
count=0
for item in $line;
do
[ $count -eq 0 ] && user=$item;
[ $count -eq 6 ] && shell=$item;
let count++
done;
IFS=$oldIFS
echo $user\'s shell is $shell;
执行结果:
[root@xx shell]# ./ifsTest2.sh 
root's shell is /bin/bash



```





