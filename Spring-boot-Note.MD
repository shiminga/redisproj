官方spring用gradle构建的，
spring-boot用maven构建的,参考
https://blog.csdn.net/w605283073/article/details/85106902


1.构建spring-boot的时候setting.xml加一下如下镜像

<mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url> http://maven.aliyun.com/nexus/content/groups/public)</url>
</mirror>
 
<mirror>  
     <id>ibiblio</id>  
     <mirrorOf>central</mirrorOf>  
     <name>ibiblio Mirror of  [http://repo1.maven.org/maven2/](http://repo1.maven.org/maven2/) </name>  
     <url> http://mirrors.ibiblio.org/pub/mirrors/maven2/</url>  
</mirror>  
 
<mirror>  
     <id>jboss-public-repository-group</id>  
     <mirrorOf>central</mirrorOf>  
     <name>JBoss Public Repository Group</name>  
     <url>http://repository.jboss.org/nexus/content/groups/public </url>  
</mirror>
 
<mirror>
  <id>repo2</id>
  <mirrorOf>central</mirrorOf>
  <name>Human Readable Name for this Mirror.</name>
  <url> http://repo2.maven.org/maven2/</url>
</mirror>
 
 
<mirror>
  <id>ui</id>
  <mirrorOf>central</mirrorOf>
  <name>Human Readable Name for this Mirror.</name>
 <url> http://uk.maven.org/maven2/ </url>
</mirror>

2.修改最外层pom.xml文件
<properties>下加

<disable.checks>true</disable.checks>


******************idea 终端用不了mvn命令***************************
用户变量 Path添加如下  idea安装目录+\plugins\maven\lib\maven3\bin   
*********************************************