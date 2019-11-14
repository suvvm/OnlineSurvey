# Pop Survey简单在线调查工具

## 1.开发描述

这是一个简单的前后端分离的在线调查工具

- 前端使用vue.js开发，最终将打包为webapp
- 前端组件使用有赞的vant组件库
- 后端使用spring + mybatis进行开发
- 数据库使用开源关系数据库MySql
- 数据源与数据监控使用阿里的Druid
- 后端服务器租用阿里云服务器
- 短信发送、邮件发送与人脸识别功能均使用阿里的对应服务

## 2.项目简介

开发一个在线调查平台，让用户可以和公司交互，进行反馈。在此平台中，公司用 户可以创建和提交调查问卷给管理员，管理员审核后可批准发布调查，然后用户可以参加调查。 调查结束后，公司用户可以查看调查数据。该项目旨在开发一个平台，使得公司可以提供在线 调查，以获得关于其新产品、现有产品、服务等的反馈。 

## 3.功能需求

### 3.1用户功能

#### 3.1.1用户注册

用户需要在注册表单中输入要求的详细信息才能注册。 用户信息包括：昵称、头像、手机、电子邮件地址、性别、 密码、人脸识别信息等，密码将采取 加密（加密方法还未选定）并保存在数据库中。 用户注册时可以选择自己感兴趣的话题或领域，这样登录后可以看到推送的相关的调查。 可以限制用户可以选择的话题或领域的个数。 

#### 3.1.2用户登录

 已注册用户可以通过输入其昵称、手机或电子邮件地址登录系统，或通过人脸识别进行登录。 用户登录后在显眼位置推送最近热门的调查和与用户感兴趣话题或领域相关的调查（默认 可按照发布时间排序）  

#### 3.1.3查看和检索调查问卷

用户可以输入关键字检索调查问卷，关键字可以是公司名、调查关键字（如防秃等），然 后显示检索到的调查问卷列表给用户，默认按照调查问卷参与人数排序，用户也可选择按照时 间进行排序，点击某个调查，用户可进入调查问卷页面，参与调查。 用户可查看自己参与过的调查的列表，并可管理列表中的调查，如删除想删除某个调查。 

### 3.2公司用户功能

#### 3.2.1公司用户注册

公司用户需要在注册表单中输入要求的详细信息才能注册。 公司用户也是一名用户，但对应权限不同。 公司用户注册的时候需要提供公司的相关信息，包括但不限于公司名称、公司类型、所属 行业、公司规模、公司简介等。 

#### 3.2.2公司用户登录

公司用户登录需求与普通用户相同

#### 3.2.3发布调查

登录的公司用户可以创建新的调查，发布调查可以选择调查类型、指定调查问卷标题、添 加调查问卷题目等，问卷题目可以是文本类型、选择题类型等。创建好调查后可以提交调查， 等待管理员审核和发布问卷。  

#### 3.2.4管理调查

公司用户可以管理自己发布的调查问卷，公司用户可以查看发布的所有调查问卷，或者基 于一定条件检索调查问卷列表，如根据关键字进行检索，检索到的列表按照参与人数降序 排序，公司用户也可选择按照发布时间降序或升序排序。点击列表中的某个调查问卷，可 以查看调查问卷的详细内容，并可进行编辑更新或删除。 

#### 3.2.5查看调查结果

调查完成后，公司用户可以登录并查看与调查相关的汇总报告，报告可以以表格、图表和 其他格式显示。  

### 3.3管理员功能

#### 3.3.1管理员账户的创建

平台管理员的账户只能由现有平台管理员创建然后共享给其他管理员使用。 

#### 3.3.2审核和管理公司人员信息

平台管理员可以查看公司用户列表，点击某个公司人员可以查看其详细信息和关联的公司的信息 ，平台管理员有权限编辑和删除公司用户信息 。

#### 3.3.3管理用户

平台管理员可以查看注册用户列表，或根据一定条件检索用户，如用户昵称、手机号、 邮箱等，平台管理员有权限编辑和删除用户信息。

#### 3.3.4管理调查问卷

平台管理员可以查看所有提交未发布的调查问卷，默认按照发布时间倒序显示调查问 卷，或者根据关键字、发布人或发布人所属公司检索调查问卷列表，并对列表中的调查进 行批准或拒绝，批准后问卷发布，用户可以参与问卷调查，未批准的调查，用户不能参与 调查。如果拒绝或批准，公司用户会收到邮件提醒。 平台管理员可以查看所有已发布的调查问卷，默认按照调查参与人数倒序显示，或者 根据关键字、发布人或发布人所属公司检索调查问卷列表，平台管理员有权限删除一个或 多个调查问卷。 

## 4.其他功能需求

### 4.1 数据量大的查询添加数据分页功能 

### 4.2  表单元素添加适当的验证，如验证密码或用户名的有效输入 

## 5.开发日志

2019年11月10日 GMT+8 下午7:56	[创建vue项目OnlineSurvey作为本次在线调查系统的前端]( https://github.com/suvvm/OnlineSurvey/commit/b9edd70eafd94963077a14cf6a717fde0022ac71 ) 

2019年11月10日 GMT+8 下午8:00	[添加快速打开命令行的工具]( https://github.com/suvvm/OnlineSurvey/commit/1f892f0ba3344b8f1ddcfd6eba74292155c9a0a3 )

2019年11月10日 GMT+8 下午8:05	[初始化npm项目创建package.json]( https://github.com/suvvm/OnlineSurvey/commit/680e96c9b494a9f0b264fdede8fed1c631fa3cd9 )

2019年11月10日 GMT+8 下午8:09	[安装vant组件库]( https://github.com/suvvm/OnlineSurvey/commit/4d8429464f210302bafed86ac50d459978119884 )

2019年11月10日 GMT+8 下午8:44	[add README.md]( https://github.com/suvvm/OnlineSurvey/commit/f15e7f518790ef6df0eedf07023a41ee60899a08 ) 

2019年11月10日 GMT+8 下午8:50	[添加.gitattributes配置文件](https://github.com/suvvm/OnlineSurvey/commit/321fa8ba7296604263d77ba9962abe9a1ee12eb8) 

2019年11月10日 GMT+8 下午9:21	[创建后台工程](https://github.com/suvvm/OnlineSurvey/commit/2979d49e78eb2b726c5d42747d84c9dcd57bc72e) 

2019年11月10日 GMT+8 下午9:21	[添加数据字典](https://github.com/suvvm/OnlineSurvey/commit/6e8dc719b6ca7b891f59a649171fb501b7533279) 

2019年11月11日 GMT+8 上午8:58	[完成vant使用配置](https://github.com/suvvm/OnlineSurvey/commit/dbcccfb5593dcf10d2f9357dfb39160f88f74f98) 

2019年11月11日 GMT+8 上午9:12	[添加log4j配置文件](https://github.com/suvvm/OnlineSurvey/commit/dd55199917ccb70044b19a10984f7b18bc97f923) 

2019年11月11日 GMT+8 下午8:06	[添加数据库脚本文件](https://github.com/suvvm/OnlineSurvey/commit/075fb9967d084d397f0d143fce520b39f40e8bec) 

2019年11月11日 GMT+8 下午10:02	[设计index页面直接为登录页，添加部分表单组件](https://github.com/suvvm/OnlineSurvey/commit/ebd54125ed7c70eacd461a519c10ef6702ad2ea2) 

2019年11月12日 GMT+8 上午10:48	[新建人脸识别登录页面loginFV](https://github.com/suvvm/OnlineSurvey/commit/e932a7e91688d08539004e638818324beced7e99) 

2019年11月12日 GMT+8 上午10:49	[新建用户注册页面](https://github.com/suvvm/OnlineSurvey/commit/93621c6c3b7fabcb059e5c4703d8823e870bcace) 

2019年11月12日 GMT+8 上午10:49	[将新页面注册至pages.json](https://github.com/suvvm/OnlineSurvey/commit/2481b43ee1f3c388a1b12423b4ab21fad8bc4e1c) 

2019年11月12日 GMT+8 上午11:54	[完善人脸登录页面]( https://github.com/suvvm/OnlineSurvey/commit/25dc74073bacc0ca3ba858195677a29d0afad758 )

2019年11月12日 GMT+8 上午11:54	[完善用户注册页面]( https://github.com/suvvm/OnlineSurvey/commit/b5d46c6caeacf984474c1337480278a3e61bfcb9 )

2019年11月12日 GMT+8 上午11:54	[添加logo图片资源]( https://github.com/suvvm/OnlineSurvey/commit/54bc66dbe38952dd2fb9cec5b330b607ef8d605e )

2019年11月12日 GMT+8 下午4:37	[添加yml配置文件](https://github.com/suvvm/OnlineSurvey/commit/8123c119d0d06b6759fa8dc13ff88ba51bb2708a) 

2019年11月12日 GMT+8 下午4:38	[更新数据库连接url](https://github.com/suvvm/OnlineSurvey/commit/501c3f271d1fd5e4984a607fcfb95291c027b091) 

2019年11月12日 GMT+8 下午4:39	[更新数据字典](https://github.com/suvvm/OnlineSurvey/commit/ebdbec7f4212ce68e429db50fd075c4cc056bcaa) 

2019年11月12日 GMT+8 下午4:39	[更新数据库脚本文件](https://github.com/suvvm/OnlineSurvey/commit/af89a3dd8fab0771e9ebf2b246bdcbcf5c81ea71) 

2019年11月12日 GMT+8 下午5:00	[创建数据库表对应JavaBean](https://github.com/suvvm/OnlineSurvey/commit/beeb078464de349d89a399fb332a3073e8abfafc) 

2019年11月12日 GMT+8 下午5:01	[完成属性添加与getter与setter创建](https://github.com/suvvm/OnlineSurvey/commit/42fc0e9496d2061d699ecfcb96691e7a70e7882a) 

2019年11月12日 GMT+8 下午5:02	[更新数据库脚本文件](https://github.com/suvvm/OnlineSurvey/commit/d15e6326c4efd45eb135ec667392148c83fdb0ba) 

2019年11月12日 GMT+8 下午5:04	[完成druid数据监控配置](https://github.com/suvvm/OnlineSurvey/commit/0eed5d76c0677076a11883c3d710717d691553ec) 

2019年11月13日 GMT+8 下午2:06	[添加数据传输测试Controller](https://github.com/suvvm/OnlineSurvey/commit/1d13ac61b00e2b9b6b43deb2fd1a08dd27099fb8) 

2019年11月13日 GMT+8 下午2:06	[新建并注册测试界面test](https://github.com/suvvm/OnlineSurvey/commit/ffdc32da52b7ea58a796296cb797107b38dca636) 

2019年11月13日 GMT+8 下午2:11	[安装request-promise](https://github.com/suvvm/OnlineSurvey/commit/78f3be0cbad3c45f8ea63bc2b0fc152b23bc6938) 

2019年11月13日 GMT+8 下午2:34	[完成前后端通信测试代码](https://github.com/suvvm/OnlineSurvey/commit/f21a5138ca6e47dda7eea590b08e48dd4e637416) 

2019年11月13日 GMT+8 下午2:40	[安装lrz图片压缩工具](https://github.com/suvvm/OnlineSurvey/commit/e612dd62d322e2ca756285d26245737643835b08) 

2019年11月13日 GMT+8 下午8:08	[完成跨域访问配置](https://github.com/suvvm/OnlineSurvey/commit/352ae7a0511115c5b744d607f1d55e0706f44044) 

2019年11月13日 GMT+8 下午8:08	[添加获取手机验证码的Controller](https://github.com/suvvm/OnlineSurvey/commit/7172a5af678e1cab9f252e3259704a5fd4f42cb2) 

2019年11月13日 GMT+8 下午8:10	[添加gson与阿里云Alibaba Cloud SDK的依赖](https://github.com/suvvm/OnlineSurvey/commit/84c47f49f954be48e8b87efe98c1d580711166ae) 

2019年11月13日 GMT+8 下午8:11	[修改部分样式](https://github.com/suvvm/OnlineSurvey/commit/0d88eda8adb29b2245b043b336f594180a457359) 

2019年11月13日 GMT+8 下午11:22	[添加计划表](https://github.com/suvvm/OnlineSurvey/commit/7a9e1547d99ebb64fd1ada7caa4dcd002a6db596) 

2019年11月13日 GMT+8 下午11:46	[添加阿里fastjson依赖](https://github.com/suvvm/OnlineSurvey/commit/a056a09cfb496d7a01bbfe6d863577b9f2b6c6e0) 

2019年11月13日 GMT+8 下午11:47	[完成手机验证码发送配置](https://github.com/suvvm/OnlineSurvey/commit/0953659a368f90fc25ef5a937a8f6b94ea0fa70f) 

2019年11月14日 GMT+8 下午4:44	[完善handleClick函数](https://github.com/suvvm/OnlineSurvey/commit/7a4d65a9166f94abfa04ab664ceaa571828b08c9) 

2019年11月14日 GMT+8 下午10:00	[添加MapperScan注解配置](https://github.com/suvvm/OnlineSurvey/commit/2cc436371606fba9c4886aca8e81ff4303eb97a7) 

2019年11月14日 GMT+8 下午10:01	[配置针对users的数据访问方法](https://github.com/suvvm/OnlineSurvey/commit/575ffdc9ed3233fbfcc5039aea618f807f318bc6) 