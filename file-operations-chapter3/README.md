# 文件操作相关的操作.
```text
  Unix将操作系统中的一切都视为文件。文件预每一个操作息息相关，而我们可以利用它们进行
各种于系统或者进程相关的处理工作。例如，我们所使用的命令终端就是和一个设备文件关联在
一起的。我们可以通过写入特定终端所对应的设备文件来实现向终端写入信息。有各种不同形式的
文件，例如目录，普通文件，块设备，字符设备，符号链接，套接字和命名管道等。文件名，大小
，文件类型，文件内容修改时间，文件访问时间，文件属性更改时间，i节点，连接以及文件所在的
文件系统等都是文件的属性。
```

## 生成任意大小的文件
```text
  由于各种可能的原因，我们可能需要生成随机文件来测试。这个时候我们可以使用通用的工具dd.dd命令
会克隆给定的输入内容，然后将一模一样的一份父辈写入到输出。stdin，设备文件，普通文件等都可以作为
输入.
[root@xx generateFile]# dd if=/dev/zero of=/usr/shell/generateFile/junk.data bs=1M count=1
1+0 records in
1+0 records out
1048576 bytes (1.0 MB) copied, 0.00144527 s, 726 MB/s
[root@xx generateFile]# ll
total 1024
-rw-r--r-- 1 root root 1048576 Jul  7 09:16 junk.data
该命令会创建一个1MB大小的文件junk.data，if代表输入文件(input file)，of代表输出文件(output file)，bs代表以字节为单位的块的大小,
count代表需要复制的块数量。/dev/zero是一个字符设备，它会不断的返回0值字节(\0).
```
|  单元大小   | 代码  |
|  ----  | ----  |
|字节(1B) | c |
|字(2B) | w |
|块(512B)| b |
|千字节(1024B)| K |
|兆字节(1024MB)| M |
|吉字节(1024MB)|G|

## 文本文件的交际与差集
```text
  交集(intersection)和差集(set difference)操作在集合论相关的数学课上经常会被用到，不过，对文本进行类似的操作在某些情况下很有用。
comm命令可以用于两个文件之间的比较。它有很多不错的选项可以用来调整输出，以便我们执行交集，求差(difference)以及求差操作
* 交集：打印出两个文件的所共有的行
* 求差：打印出指定文件的所包含的且不相同的那些行
* 差集：打印出包含在文件A中的，但是不包含在其他指定的文件中的那些行.

但是在使用comm命令的时候需要注意，一定要使用排序过的文件才能够进行对比
例子：
[root@xx generateFile]# cat A.txt 
qefdgsdda
qewqeqw
qwejqwe
qwewqeqw
[root@xx generateFile]# cat B.txt 
hello world
qefdgsdda
qewqeqw
qwejqwe
qwewqeqw
[root@xx generateFile]# comm A.txt B.txt 
	hello world
		qefdgsdda
		qewqeqw
		qwejqwe
		qwewqeqw


由于文件是随便写的，所以没有看出效果，但是实际上第一列是只有出现在A文件中的，第二列是只出现在B文件中的，第三列是既出现在A文件中也出现
在B文件中的。
格式话选项：
* -1从输出中删除第一列
* -2从输出中删除第二列
* -3从输出中删除第三列

例子(从输出中删除第一第二列)
[root@xx generateFile]# comm A.txt B.txt -1 -2
qefdgsdda
qewqeqw
qwejqwe
qwewqeqw

```


## 查找并删除重复的文件
待完成
````text

````

## 创建长路径
```text
  有些情况我们需要创建一个空的目录树。如果给定路径中包含目录，那么还必须检查这些目录是否已经存在。这个时候就会使得代码变得很臃肿
而且低效。
  mkdir可以用于创建目录。mkdir dirpath 如果目录已经存在,会返回"File exist"的错误提示,如果我们需要创建一个目录，那么我们需要创建路径下
的目录，我们需要判断去创建：
if [ -e /home/chendom ]; then
mkdir path
fi
-e是一个用在条件判断[]中的参数，可用于判断某个文件是否存在，在类UNIX系统中，目录同样是一种文件，如果文件存在，[ -e FILE_PATH ]返回真
上面的方式显得比较繁琐，mkdir -p可以取代上面的繁琐的命令。
mkdir -p  demo/demo2
```

## 文件权限，所有权和粘滞位
```shell script
  文件权限和所有权是UNIX/Linux文件系统(入ext文件系统)最显著的特性之一。在UNIX/Linux平台工作时，我们经常会碰到于文件权限以及所
有全相关的问题。这则攻略就考察了文件权限和所有权不同用例。
  我们通常要和三类权限打交道(用户，用户组以及其他实体)，用户(user)是文件的所有者。用户组(group)是多个用户的集合，系统允许这些用户进行
某些形式的访问。其他用户(other)是除用户或者用户组之外的任何用户。
1. 查看文件的类型：
[root@xx shell]# ls -l
total 252
-rw-r--r-- 1 root root     15 Jun 28 10:56 args.txt
-rwxrwxrwx 1 root root   1146 Jun 16 14:09 arithmetic.sh
-rwxrwxrwx 1 root root     24 Jun 28 10:13 cecho.sh
-rw-r--r-- 1 root root     40 Jun 30 10:07 data3.txt
第一列的表示的文件的类型
* "-":普通文件
* "d":目录
* "c":字符设备
* "b":块设备
* "l":符合连接
* "s":套接字
* "p":管道

2.给文件赋权

为了给文件设置权限，可以使用chmod命令
例子： chmod u=rwx g=rw o=r filename
我们也可以使用+对用户权限进行添加，使用-删除用户的权限

读，写，执行都有与之对应的八进制数:
* r--=4
* -w-=2
* --x=1
3. 更改所有权
chown user.group filename

4.设置粘滞位
  粘滞位是一种应用于目录的权限类型。通过设置粘滞位，使得只有目录的所有者才能够删除目录中的文件，即使用户组合其他用户拥有足够的权
限也不能执行该删除的操作。

5.以递归的方式设置权限
有些特殊的情况需要在目录下面对某些文件进行递归来修改文件的权限
chmod 777 . -R
例子：
[root@xx demo]# ll
total 8
drwxr-xr-x 2 root root 4096 Jul  6 11:35 demo2
drwxr-xr-x 2 root root 4096 Jul  6 10:29 renameFiles
[root@xx demo]# chmod 777 . -R
[root@xx demo]# ll
total 8
drwxrwxrwx 2 root root 4096 Jul  6 11:35 demo2
drwxrwxrwx 2 root root 4096 Jul  6 10:29 renameFiles
[root@xx demo]# cd renameFiles/
[root@xx renameFiles]# ll
total 8
-rwxrwxrwx 1 root root  14 Jul  6 09:00 hello_world.txt
-rwxrwxrwx 1 root root 163 Jul  6 10:29 renameFile.sh

6. 以递归的方式设置所有权
chown user.group . -R
```

## 创建不可修改的文件
```text
  在常见的Linux扩展文件系统中(如ext2,ext3,ext4等)，可以将文件设置为不可修改的(immutable)。某些文件属性可以帮助我们将文件设置为
不可修改的。一旦文件被设置为不可修改，任何用户，包括着急用户都不能删除文件，除非其不可修改的属性被移除。通过查看/etc/mtab文件，很
容易找出所有挂载分区的文件系统的类型。
  我们可以使用chattr将文件设置为不可修改。不过chattr能够更改的扩展属性可不止这个，a（仅追加）、c（压缩）、d（不转储）、
i（不可更改）、j（数据日志）、s（安全删除）、t（无尾部合并）、u（不可删除）、A（不更新访问时间atime）、D（同步目录更新）、
S（同步更新）和T（目录层级结构顶部）
例子：
[root@xx renameFiles]# chattr +i chattrDemo 
[root@xx renameFiles]# vim chattrDemo 
效果入下图所示

```
![chattr设置文件不可修改](files/chattrfile.png)
 


