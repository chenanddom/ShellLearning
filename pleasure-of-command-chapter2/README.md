# Linux的命令
```text
  Linux的各种命令可以帮助我们完成各种繁杂的任务，使得我们的工作可以边得更加的轻松
```

## cat命令的使用
```text
  cat是命令通常用于读取，显示或者凭借文件内容，不过它所具备的能力远远不止这些。cat常用的参数选项有如下几个

-A、 --显示所有等效于-vET

-b、 --数字非空数字非空输出行，覆盖-n

-e等价于-vE

-E、 --每行末尾显示$

-n、 --所有输出行的编号

-s、 --挤压空白抑制重复的空输出行

-t相当于-vT

-T、 --显示选项卡将选项卡字符显示为^I

-u（忽略）

-v、 --显示非打印使用^和M-符号，除了LFD和TAB

例子1(-s选项只可以去掉重复的空白行)：
[root@xx shell]# cat -s test.txt 
1

2

3
例子2(tr可以移除空白行，甚至可以将连续的多个'\n'字符压缩成单个'\n')
[root@xx shell]# cat  test.txt |tr -s  '\n'
1
2
3
```
**将制表符效时为^|**
```text
对于一些编程语言，我们空格和指标符都是可以作为缩进的，但是所具备的含义不尽相同。cat可以使用-T选项来将指标符
标记成为^|
例子：
[root@xx shell]# cat -T test.txt 
1

2
^I^I^I^I^I^I
3


```

## 录制与回放终端会话
```text
  当你需要为别人在终端上演示某些操作或者是需要准备一个命令行教程时，通常你要边手动输入一遍演示，或者,你也可以录制
一段屏幕演示视频，然后再回放出来。使得看到的回放视频就好像是在现在再输入命令一般。我们可以使用script和scriptreplay命令来
实现。

例子：
[root@xx shell]# script -t 2> timing.log -a output.session
Script started, file is output.session
結束的時候要是有exit退出录制
再下面的图中可以看到有timing.log(存储时许信息)和output.session(存储命令输入信息)，我们可以按照下面的方式执行
上面的命令回放
[root@xx shell]# scriptreplay timing.log output.session 
```
![录制与回放终端会话](files/script_scriptreplay.png)

**终端录制的原理**
```text
  通常，我们会录制桌面环境视频来作为教程使用。不过要注意的是，视频需要大量的存储空间来存储，而终端脚本文件
仅仅是一个文本文件，起文件大小不过是哥KB级别的大小。
  script命令同样可以建立可在多个用户之间进行广播的视频会话，这是一件很有意思的事，实现的步骤如下:
1.分别打开两个终端teminal1和teminal2.
2. 在终端使用如下的命令创建scriptfifo文件(可以看到已经创建了一个scriptfifo文件)
[root@xx shell]# mkfifo scriptfifo
[root@xx shell]# ll
total 92
-rwxrwxrwx 1 root root 1146 Jun 16 14:09 arithmetic.sh
-rwxrwxrwx 1 root root  105 Jun 18 13:39 debugScript.sh
-rw-r--r-- 1 root root   94 Jun 17 10:09 error.txt
-rwxrwxrwx 1 root root   41 Jun 18 15:53 exportFunction.sh
-rwxrwxrwx 1 root root  228 Jun 19 12:47 ifsTest2.sh
-rwxrwxrwx 1 root root  123 Jun 19 11:03 ifsTest.sh
-rw-r--r-- 1 root root   67 Jun 17 12:40 log.txt
-rwxrwxrwx 1 root root  254 Jun 18 09:23 myDate.sh
-rw-r--r-- 1 root root  280 Jun 19 09:19 out
-rw-r--r-- 1 root root 4582 Jun 22 10:31 output.session
-rw-r--r-- 1 root root  381 Jun 18 20:48 out.txt
-rwxrwxrwx 1 root root  101 Jun 17 12:40 redirectionFile.sh
prw-r--r-- 1 root root    0 Jun 22 19:35 scriptfifo
-rwxrwxrwx 1 root root  161 Jun 18 10:26 sleep.sh
-rw-r--r-- 1 root root   47 Jun 17 10:47 stderr.txt
-rw-r--r-- 1 root root   47 Jun 17 10:51 stdout.txt
-rwxrwxrwx 1 root root   13 Jun 19 10:01 subScript.sh
-rwxrwxrwx 1 root root   34 Jun 19 09:51 subShell.sh
-rwxrwxrwx 1 root root  139 Jun 18 15:30 success_test.sh
-rw-r--r-- 1 root root 1473 Jun 17 11:24 temp.txt
-rw-r--r-- 1 root root   14 Jun 22 10:20 test.txt
-rw-r--r-- 1 root root  809 Jun 22 10:31 timing.log
-rwxrwxrwx 1 root root   64 Jun 16 10:18 variables.sh

3. 在teminal1中输入
[root@xx shell]# script -f scriptfifo 
Script started, file is scriptfifo


4. 在terminal2中输入如下的命令
[root@xx shell]# cat scriptfifo 
Script started on Mon 22 Jun 2020 07:48:28 PM CST

经过上面步骤的执行可以看到无论在teminal1执行了上面命令，在teminal2都会同步
```
![多终端同时查看](files/screnn_record.png)


## 文件查找与文件列表
```text
  find是Unix/Linux命令行工具箱中比较强大的工具之一，这个命令能给我们带来很多的帮助，为此我们需要对它
有比较深入的理解。
  find命令的工作方式如下:沿着文件层次结构向下遍历，匹配符合条件的文件，并且指向相应的操作。
例子(打印文件和目录的列表)：
[root@xx shell]# find . -print
.
./subScript.sh
./subShell.sh
./scriptfifo
./error.txt
./redirectionFile.sh
./myDate.sh
./ifsTest2.sh
./test.txt
./sleep.sh
./variables.sh
./arithmetic.sh
./output.session
./log.txt
./success_test.sh
./ifsTest.sh
./debugScript.sh
./out
./exportFunction.sh
./temp.txt
./stderr.txt
./timing.log
./stdout.txt
./out.txt

-print指明打印出匹配文件的文件名(路径)。当使用-print选项时，'\n'作为用于分割文件的界定符
-print0指明使用'\n'作为界定符来打印每一个匹配的文件名。当文件名中包含换行符时，这个方法就有了用武之地。
```

**根据文件名或者正则表达式匹配搜索**
```text
1. 选项-name的参数指定了文件名所必须匹配的字符串。我们可以将通配符作为参数使用。*.txt能够匹配所有以.txt结尾的文件名。
选项-print在终端打印文件名或者文件路径。
例子:
[root@xx shell]# find /usr/shell -name "*.txt"
/usr/shell/error.txt
/usr/shell/test.txt
/usr/shell/log.txt
/usr/shell/temp.txt
/usr/shell/stderr.txt
/usr/shell/stdout.txt
/usr/shell/out.txt
选项iname(忽略大小写),该选项的作用和-name类似，只不过在匹配名字的时候会忽略大小写。
[root@xx shell]# ll
total 92
-rwxrwxrwx 1 root root 1146 Jun 16 14:09 arithmetic.sh
-rwxrwxrwx 1 root root  105 Jun 18 13:39 debugScript.sh
-rw-r--r-- 1 root root   94 Jun 17 10:09 error.txt
-rwxrwxrwx 1 root root   41 Jun 18 15:53 exportFunction.sh
-rwxrwxrwx 1 root root  228 Jun 19 12:47 ifsTest2.sh
-rwxrwxrwx 1 root root  123 Jun 19 11:03 ifsTest.sh
-rw-r--r-- 1 root root   67 Jun 17 12:40 log.txt
-rwxrwxrwx 1 root root  254 Jun 18 09:23 myDate.sh
-rw-r--r-- 1 root root  280 Jun 19 09:19 out
-rw-r--r-- 1 root root 4582 Jun 22 10:31 output.session
-rw-r--r-- 1 root root  381 Jun 18 20:48 out.txt
-rwxrwxrwx 1 root root  101 Jun 17 12:40 redirectionFile.sh
prw-r--r-- 1 root root    0 Jun 22 20:13 scriptfifo
-rwxrwxrwx 1 root root  161 Jun 18 10:26 sleep.sh
-rw-r--r-- 1 root root   47 Jun 17 10:47 stderr.txt
-rw-r--r-- 1 root root   47 Jun 17 10:51 stdout.txt
-rwxrwxrwx 1 root root   13 Jun 19 10:01 subScript.sh
-rwxrwxrwx 1 root root   34 Jun 19 09:51 subShell.sh
-rwxrwxrwx 1 root root  139 Jun 18 15:30 success_test.sh
-rw-r--r-- 1 root root 1473 Jun 17 11:24 temp.txt
-rw-r--r-- 1 root root   14 Jun 22 10:20 test.txt
-rw-r--r-- 1 root root    0 Jun 23 09:19 TEST.txt
-rw-r--r-- 1 root root  809 Jun 22 10:31 timing.log
-rwxrwxrwx 1 root root   64 Jun 16 10:18 variables.sh
[root@xx shell]# find -iname "test*" -print 
./test.txt
./TEST.txt

2. 如果我们希望匹配多个条件中的一个，可以采取OR条件操作：
例子：
[root@xx shell]# find . \( -name "*.txt" -o -name "*.pdf" \) -print
./error.txt
./test.txt
./TEST.txt
./demo.pdf
./log.txt
./temp.txt
./stderr.txt
./stdout.txt
./out.txt


3. 选项-path的参数可以使用通配符来匹配文件路径或者文件。-name总是用给定的文件名进行匹配。-path则将文件路径作为一个
整体进行匹配。
例子：
[root@xx shell]# find /usr/shell/  -path "*demo*" -print
/usr/shell/demo
/usr/shell/demo/demo2
/usr/shell/demo/demo2/demo.txt
/usr/shell/demo/demo.txt
/usr/shell/demo.pdf

4. 选项-regex的参数和-path类似，只不过-regex是基于正则表达式来匹配文件路径的。正则表达式式通配符的高级形式，
-iregex忽略大小写
例子(匹配.txt和.pdf结尾的文件)
[root@xx shell]# find . -regex ".*\(\.txt\|\.pdf\)$"
./error.txt
./demo/demo2/demo.txt
./demo/demo.txt
./test.txt
./TEST.txt
./demo.pdf
./log.txt
./temp.txt
./stderr.txt
./stdout.txt
./out.txt

5. 否定参数
find可以使用"!"实现参数否定
例子：
[root@xx shell]# find . ! -name "*.txt" -print
.
./subScript.sh
./subShell.sh
./scriptfifo
./redirectionFile.sh
./myDate.sh
./ifsTest2.sh
./demo
./demo/demo2
./sleep.sh
./variables.sh
./demo.pdf
./arithmetic.sh
./output.session
./success_test.sh
./ifsTest.sh
./debugScript.sh
./out
./exportFunction.sh
./timing.log

6. 基于目录的深度的搜索
这个一般使用 -maxdept和-mindept选项来实现深度

7. 根据文件类型进行搜索
类UNIX系统将一些都视为文件，文件具有不同的类型(普通文件，目录，字符设备，块设备，符号连接，硬连接)
-type可以对文件搜索进行过滤。
例子:
[root@xx shell]# find . -type d -print
.
./demo
./demo/demo2
./demo/demo.txt


```






