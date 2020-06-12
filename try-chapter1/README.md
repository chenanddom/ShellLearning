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


