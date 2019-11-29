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
- 如阿里云accessKey等敏感信息本项目单独用了一个配置文件application-privateInfo.yml进行配置，该文件并未上传，需要在后端src/main/resources中新建该文件并添加对应配置。

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

2019年11月15日 GMT+8 上午12:11	[创建包含companies的数据访问方法的mapper CmpMapper](https://github.com/suvvm/OnlineSurvey/commit/2899825de80ae2445e665fccdeabbaf57ffd0ab6) 

2019年11月15日 GMT+8 上午12:11	[添加公司Update方法](https://github.com/suvvm/OnlineSurvey/commit/48f644985d437e3f5f6c94afa94d465012dd8872) 

2019年11月15日 GMT+8 上午12:13	[创建company动态sql的Provider类CmpMapperProvider 添加select方法](https://github.com/suvvm/OnlineSurvey/commit/7a38cde3311d7cb2a9aad314b49185d2046b0a56) 

2019年11月15日 GMT+8 上午10:21	[UserMapper修复updateUser SQL语句问题](https://github.com/suvvm/OnlineSurvey/commit/6fe96daf55917262cdbdcd0914bf6089b05332fe) 

2019年11月15日 GMT+8 上午10:23	[CmpMapper完成插入删除更新](https://github.com/suvvm/OnlineSurvey/commit/387bd14d865365a36bcb54660b5d50c32aeb6799) 

2019年11月15日 GMT+8 上午11:00	[CmpMapper修改错误注释 添加插入方法Options配置](https://github.com/suvvm/OnlineSurvey/commit/814f47f1f3526be539901fd11a7ae217996f1618) 

2019年11月15日 GMT+8 上午11:02	 [创建TagMapper配置针对taps的数据访问方法](https://github.com/suvvm/OnlineSurvey/commit/3a65518287200a55f7e8cce202da3de51cbe990a) 

2019年11月18日 GMT+8 上午9:01	[添加UserController](https://github.com/suvvm/OnlineSurvey/commit/f70bb4fde180ca32f358281a1d51f304db17cc65) 

2019年11月18日 GMT+8 上午9:01	[添加数据库测试Controller DBTestController](https://github.com/suvvm/OnlineSurvey/commit/77697ccbdcb733b54bd14a6803fa5a89b809d554) 

2019年11月18日 GMT+8 上午9:02	[编写数据库测试模块](https://github.com/suvvm/OnlineSurvey/commit/c68b5ffcdae21a854e6d3ad2f8426d1b6630e998) 

2019年11月18日 GMT+8 下午8:31	[添加手机验证码发送测试方法](https://github.com/suvvm/OnlineSurvey/commit/82b5a4be8610b67563a5682915d063f928a3ecfd) 

2019年11月18日 GMT+8 下午9:53	[UserMapper修正更新与插入中表名错误问题](https://github.com/suvvm/OnlineSurvey/commit/877a9193562dcf717be57665e4842c85647bfaf6) 

2019年11月18日 GMT+8 下午9:54	[junit添加用户插入测试方法](https://github.com/suvvm/OnlineSurvey/commit/0d246c1186d1b991ea3c67a27de03287d890151b) 

2019年11月18日 GMT+8 下午9:56	[UserController完成针对接收前端数据新增用户的配置](https://github.com/suvvm/OnlineSurvey/commit/7417ce471a47ea53bcc42d282c345bf001401942) 

2019年11月18日 GMT+8 下午9:56	[添加测试用户信息](https://github.com/suvvm/OnlineSurvey/commit/3ecbf42aca83e8377a63dac9c37a71201d151555) 

2019年11月18日 GMT+8 下午9:57	[添加lrz图片压缩工具的import](https://github.com/suvvm/OnlineSurvey/commit/14a51cc479ae42d9aa94520698a8de6873797e44) 

2019年11月18日 GMT+8 下午10:02	[register页面逻辑基本完成](https://github.com/suvvm/OnlineSurvey/commit/ff60b153429bfac29fbb88168a524a317d30791a) 

2019年11月18日 GMT+8 下午10:08	[创建用于处理人脸识别请求的Controller FVerifyController](https://github.com/suvvm/OnlineSurvey/commit/a5ca6cdbe284684b9f042cb60dabc134bb47d68a) 

2019年11月18日 GMT+8 下午10:29	[创建人脸识别配置工具 FVerifyUtil](https://github.com/suvvm/OnlineSurvey/commit/823733f9d96376e39ae33c0cafb2287c1f612276) 

2019年11月18日 GMT+8 下午10:30	[完成人脸识别配置工具 FVerifyUtil](https://github.com/suvvm/OnlineSurvey/commit/b54ba1f4cd1592988729b82b653fa8fb91334d74) 

2019年11月18日 GMT+8 下午10:33	[完成FVerifyController](https://github.com/suvvm/OnlineSurvey/commit/6e06ab1a259cd5bb587ef2cce827d2f4cce08366) 

2019年11月19日 GMT+8 下午3:59	[更新忽略文件 忽略隐私信息配置文件application-privateInfo.yml](https://github.com/suvvm/OnlineSurvey/commit/4e3c45f8fbfd943036c45df7ecf828cced04ea51) 

2019年11月19日 GMT+8 下午4:01	[FVerifyController.java人脸识别时将一并把用户详细数据返回给前端](https://github.com/suvvm/OnlineSurvey/commit/98d72914fcd9ae77632a5a8fd79ffa4289943ff3) 

2019年11月19日 GMT+8 下午4:05	[登录页与注册页添加提交加载的显示](https://github.com/suvvm/OnlineSurvey/commit/6c412b44990e370bdb3c4efdbd76458c39256fd0) 

2019年11月19日 GMT+8 下午4:06	[基本完成前端人脸识别登录的逻辑](https://github.com/suvvm/OnlineSurvey/commit/12e3d70b045ad5864726fc7bdeb92ae9d6ac25e8) 

2019年11月19日 GMT+8 下午4:44	[安装vue-cookies](https://github.com/suvvm/OnlineSurvey/commit/54856489a75dc35d04bd8820255960f89aaff140)

2019年11月19日 GMT+8 下午4:49	[将阿里云accessKey相关敏感信息保存在配置文件application-privateInfo.yml中](https://github.com/suvvm/OnlineSurvey/commit/2a949add8ed4bf21dbaf60a040ed61aaf617f55d) 

2019年11月19日 GMT+8 下午4:49	[添加敏感信息读取测试](https://github.com/suvvm/OnlineSurvey/commit/7f016d18203163769b3024c9d9075c2b34621602) 

2019年11月19日 GMT+8 下午5:16	[前端添加并注册角色选择页](https://github.com/suvvm/OnlineSurvey/commit/6aa12dbc38ad7e99a30e0f8aef104fb6189b0080) 

2019年11月19日 GMT+8 下午10:23	[修正CmpMapper中insterCmp sql语句错误](https://github.com/suvvm/OnlineSurvey/commit/173c7e4eff1d6c3e8f908df76658e9a4db7f59d5) 

2019年11月19日 GMT+8 下午10:25	[UserMapper修正updateUserById传值错误](https://github.com/suvvm/OnlineSurvey/commit/86d61f1216e1ae0e5d8f67b3ce346b0cbecee429) 

2019年11月19日 GMT+8 下午10:26	[封装MD5加密工具](https://github.com/suvvm/OnlineSurvey/commit/9de65f511847bf7712420f031551deba6901ef6e) 

2019年11月19日 GMT+8 下午10:27	[创建处理公司相关请求的Controller CmpController](https://github.com/suvvm/OnlineSurvey/commit/a3bb52c4776f6bf339ca6eddda7e0b63ba3f0b1f)

2019年11月19日 GMT+8 下午10:29	[添加注册完成页](https://github.com/suvvm/OnlineSurvey/commit/0ea2c112bd9fe28bcd74f6251a980fad88e63b65) 

2019年11月19日 GMT+8 下午10:29	[添加注册普通用户页](https://github.com/suvvm/OnlineSurvey/commit/a90faf21a5cffefcae00f3a3995e8ce45ae05338) 

2019年11月19日 GMT+8 下午10:30	[添加注册公司页](https://github.com/suvvm/OnlineSurvey/commit/cee6f2a887a968c5be69d97697f4b41847e6fb2b) 

2019年11月19日 GMT+8 下午10:30	[添加tag页](https://github.com/suvvm/OnlineSurvey/commit/33618c392f000d2f8e98a241cb33ea1c527e01dc) 

2019年11月19日 GMT+8 下午10:30	[添加用户信息页](https://github.com/suvvm/OnlineSurvey/commit/0d8f751b52bcd2c15a3af7dd4364d6a969cc323d) 

2019年11月19日 GMT+8 下午10:31	[注册新页面](https://github.com/suvvm/OnlineSurvey/commit/bba0a55dce45d4ad08c2c3d21c9ec5124bd9c8d2) 

2019年11月19日 GMT+8 下午10:32	[完成登录标识](https://github.com/suvvm/OnlineSurvey/commit/4935ccbb2cf9d0b76a4cb2e7cc318c7d8466949f) 

2019年11月19日 GMT+8 下午10:32	[完成用户选择页](https://github.com/suvvm/OnlineSurvey/commit/f004634c52d6e119928a93c5cf98dd40db62dda2) 

2019年11月19日 GMT+8 下午10:36	[完善页面路由](https://github.com/suvvm/OnlineSurvey/commit/948ca11b7bf37eaae799bf5bb358e3edaf0688da) 

2019年11月19日 GMT+8 下午10:40	[新增管理员申请页面](https://github.com/suvvm/OnlineSurvey/commit/d21cd30b60571ea155e44b60eeacdcbea83f4fe9) 

2019年11月19日 GMT+8 下午10:40	[完成用户注册分类路由](https://github.com/suvvm/OnlineSurvey/commit/f4a3442fddd43fd6cc5778dd91003f7b1ac3a7a0) 

2019年11月20日 GMT+8 上午11:50	[SmsController 替换过时方法](https://github.com/suvvm/OnlineSurvey/commit/9ce85cf8528e3ab5a17ddec1bccba56a9ef8e37d) 

2019年11月20日 GMT+8 下午3:08	[配置导航栏](https://github.com/suvvm/OnlineSurvey/commit/ede197d023a15a21dc0feccc4656bc769f8968e7) 

2019年11月20日 GMT+8 下午3:09	[引用js-pinyin](https://github.com/suvvm/OnlineSurvey/commit/98be2e0ed94756d2e9e577cd5ccab4a255c4cb85) 

2019年11月21日 GMT+8 下午2:47	[添加insertTag的测试](https://github.com/suvvm/OnlineSurvey/commit/1e7de31c4b41e5fc9b05773b07f51c8c519600c4) 

2019年11月21日 GMT+8 下午2:51	[创建处理tag相关请求的Controller TagController](https://github.com/suvvm/OnlineSurvey/commit/efacf131a0ea6646201f0dc547fb0909fcc7d63d) 

2019年11月21日 GMT+8 下午2:52	[新增并在package.json中注册页面addtag](https://github.com/suvvm/OnlineSurvey/commit/6ce5c93c1192740b668c5f30461b32f9e95088d1) 

2019年11月21日 GMT+8 下午2:53	[完成用户选择标签页的显示与选择功能](https://github.com/suvvm/OnlineSurvey/commit/bc9b2592e679ead4da391c971bb41adb22481f4f) 

2019年11月21日 GMT+8 下午7:40	[mybatis完成user与tag多对多映射](https://github.com/suvvm/OnlineSurvey/commit/ff18956e9754722568b7540035d42dc899332745) 

2019年11月21日 GMT+8 下午7:41	[添加对user与tag的查询测试](https://github.com/suvvm/OnlineSurvey/commit/c80a16f9d1d39fd90dc383e064adc2bbc3febc2c) 

2019年11月21日 GMT+8 下午7:43	[UserControler 完成对添加user与tag关系的请求的处理](https://github.com/suvvm/OnlineSurvey/commit/7376e918e4bbc454edf7af53fd75c455e8029d1b) 

2019年11月21日 GMT+8 下午8:45	[loginFV 修正cookies格式](https://github.com/suvvm/OnlineSurvey/commit/4e3bd079a44368580d91ef5a52e74fc789abe66b) 

2019年11月21日 GMT+8 下午8:46	[registerOrdinaryUsers 完成用户选择标签功能](https://github.com/suvvm/OnlineSurvey/commit/d5c18d000ac2a4f27f4c2153abb05d8b7e12ee91) 

2019年11月21日 GMT+8 下午9:12	[registerOrdinaryUsers 完善页面路由](https://github.com/suvvm/OnlineSurvey/commit/25dde5193b29f9188c1003b0f0921c43f9e94f62) 

2019年11月21日 GMT+8 下午9:12	[添加静态资源文件sadPanda](https://github.com/suvvm/OnlineSurvey/commit/4d036f2ed40ae78caf4a527124c4299c9f94ad11) 

2019年11月21日 GMT+8 下午9:14	[registerAdmin 完成管理员注册页面设计](https://github.com/suvvm/OnlineSurvey/commit/74c370240733a91fceb66aa8dcde5ee71b3ef486) 

2019年11月21日 GMT+8 下午9:22	[修改表结构设计](https://github.com/suvvm/OnlineSurvey/commit/f6a9628a892acd02c6c91262f213157a7125c027) 

2019年11月21日 GMT+8 下午9:32	[修改数据库表结构](https://github.com/suvvm/OnlineSurvey/commit/6fa17462192e1494336d0d7f54bc5b795a90f5c8) 

2019年11月21日 GMT+8 下午10:06	[Investigate类修正数据结构](https://github.com/suvvm/OnlineSurvey/commit/e278febb2bf45bba325fd58f770926880fbeaa0d) 

2019年11月21日 GMT+8 下午10:07	[添加针对investigates的数据访问的InvMapper](https://github.com/suvvm/OnlineSurvey/commit/5d82a0a1db87451e2212f11ed7c9fce847f47b57) 

2019年11月25日 GMT+8 上午8:58	 [添加用户与公司一对一映射](https://github.com/suvvm/OnlineSurvey/commit/82a2924da116f10987add7763f5a9d775ded815b) 

2019年11月25日 GMT+8 上午9:00	 [InvMapper 修正缩进格式](https://github.com/suvvm/OnlineSurvey/commit/2a3fe995ef7043026ab350f686dcc6c8aae4d969) 

2019年11月25日 GMT+8 上午9:14	 [CmpController insertCmp修正对用户对应cmp_id的修改](https://github.com/suvvm/OnlineSurvey/commit/41082e3e5d9da37caa7d72750be6d0dd11161c04) 

2019年11月25日 GMT+8 上午9:16	[添加对查询用户的测试](https://github.com/suvvm/OnlineSurvey/commit/559a9030c4dcea887a8a16b477be889b06b958ac) 

2019年11月25日 GMT+8 上午9:37	[添加用户与问卷的一对多映射](https://github.com/suvvm/OnlineSurvey/commit/0bec659739658a3472dea260c652c574f35633a4) 

2019年11月25日 GMT+8 下午9:37	[修改问卷表结构](https://github.com/suvvm/OnlineSurvey/commit/da71be55034e30fdb2d9375730f54c543fe349d0) 

2019年11月26日 GMT+8 下午8:30	[重命名addTag为mdfTag 基本完成标签管理功能](https://github.com/suvvm/OnlineSurvey/commit/4bca323c44514e80d701435359dd9ebe1e0de2aa) 

2019年11月26日 GMT+8 下午8:32	[TagMapper 添加不与user映射的tag查询方法](https://github.com/suvvm/OnlineSurvey/commit/32d401485afd1c7ed98456d5c3d25d0ed7d747e1) 

2019年11月26日 GMT+8 下午8:33	[TagController 添加处理管理tag请求的方法](https://github.com/suvvm/OnlineSurvey/commit/1999e2181f745113f7ec3ec047a121f30f6d719a) 

2019年11月26日 GMT+8 下午8:34	[修改原addtag页的注册信息为mdfTag](https://github.com/suvvm/OnlineSurvey/commit/8f095a9085d9d7d2440cbc47f83b27055264efcc) 

2019年11月26日 GMT+8 下午8:34	[添加不与user映射的tag查询的测试方法](https://github.com/suvvm/OnlineSurvey/commit/ceb84a95fcbc3e16006a94651172bb73691ce820) 

2019年11月26日 GMT+8 下午8:41	[mdfTag 添加下拉刷新功能](https://github.com/suvvm/OnlineSurvey/commit/2f969b14569a7fa4ac2fdea510f1a36fb11a46ad) 

2019年11月26日 GMT+8 下午8:59	[mdfTag修改错误函数调用](https://github.com/suvvm/OnlineSurvey/commit/4839df3337b3e546134655d9c63e187fcea7c485) 

2019年11月26日 GMT+8 下午10:04	[创建并注册新增问卷页面 addInvestigate](https://github.com/suvvm/OnlineSurvey/commit/748d213d75ac28233bb6a3bf9eaf298fb77fc94f) 

2019年11月26日 GMT+8 下午10:05	[添加阿里云 aliyun-java-sdk-dm 依赖](https://github.com/suvvm/OnlineSurvey/commit/5fbe902712d743f71cecacf76ce5123fc65f48bd)

2019年11月26日 GMT+8 下午10:06	[创建处理邮件发送请求的Controller EmailController](https://github.com/suvvm/OnlineSurvey/commit/ad9761f5ce9c340ff71610ee6390b33172b867f9) 

2019年11月26日 GMT+8 下午10:07	[OnlinesurveyApplicationTests 添加发送邮件测试方法](https://github.com/suvvm/OnlineSurvey/commit/2b9a242eaecc60ee1c65e283a922471a3755d601) 

2019年11月27日 GMT+8 下午8:59	[addInvestigates 基本完成所需功能](https://github.com/suvvm/OnlineSurvey/commit/88038b7d2aed0046676814bd03caa398c898825a) 

2019年11月27日 GMT+8 下午9:49	[addInvestigates 增加添加问卷请求发送的数据](https://github.com/suvvm/OnlineSurvey/commit/29ed88e0e238678bbc41a1dd22d3a931ac6782fc) 

2019年11月27日 GMT+8 下午9:50	[InvMapper 添加插入问卷与tag映射关系的方法](https://github.com/suvvm/OnlineSurvey/commit/dd214c2a5b8c420cf72543c1ad6e5d96c2aaf213) 

2019年11月27日 GMT+8 下午9:51	[创建处理问卷相关请求的Controller InvController](https://github.com/suvvm/OnlineSurvey/commit/d53adbf29684498454cb60aed79cba88d55f639b) 

2019年11月27日 GMT+8 下午9:57	[创建并注册审核问卷页面 verifyInvestigates](https://github.com/suvvm/OnlineSurvey/commit/31b3b766e3cd91adb86947d759dbd686a9e52629) 

2019年11月27日 GMT+8 下午9:59	[InvMapper 修改查询动态sql生成方法](https://github.com/suvvm/OnlineSurvey/commit/69b12f79366c46d1f547d4cc44b1d2670248ab09) 

2019年11月27日 GMT+8 下午10:09	[InvMapper 修正查询方法名](https://github.com/suvvm/OnlineSurvey/commit/6812a6972381273f6041a1e2f997b1ea2b3bf21d) 

2019年11月27日 GMT+8 下午10:10	[InvController 添加处理获取未审核问卷的请求](https://github.com/suvvm/OnlineSurvey/commit/8673fc39823a8a7c5527a6edfa85ee016e85063d) 

2019年11月27日 GMT+8 下午10:24	[创建并注册审核详情页 verifyInvDetails](https://github.com/suvvm/OnlineSurvey/commit/6b7e32b5340e6a98f2464f47e5606c66de45e573) 

2019年11月27日 GMT+8 下午10:25	[InvController 修改获取未审核问卷的方法](https://github.com/suvvm/OnlineSurvey/commit/053b4486c79a17f5d9080067856071150d0b0ceb) 

2019年11月27日 GMT+8 下午10:26	[erifyInvestigates 添加问卷显示功能](https://github.com/suvvm/OnlineSurvey/commit/b5616df8046f7fabdfb2b17658d972738e347f84) 

2019年11月27日 GMT+8 下午10:31	[verifyInvestigates 添加通过点击对应问卷单元格向verifyInvDetails传值路由](https://github.com/suvvm/OnlineSurvey/commit/7216a57c4f796235baeb3b72529e21f7708e7360) 

2019年11月28日 GMT+8 下午9:15	[提取邮件发送方法](https://github.com/suvvm/OnlineSurvey/commit/70ecde0cbfe5fcc0b98e2f93ef0c08c59a29f773) 

2019年11月28日 GMT+8 下午9:16	[verifyInvestigates 修正变量名全部为驼峰命名法](https://github.com/suvvm/OnlineSurvey/commit/0a04c7f40abcaf5326bf9bdf8f020d203ea8ecdf) 

2019年11月28日 GMT+8 下午9:17	[OnlinesurveyApplicationTests 修改邮件发送测试方法](https://github.com/suvvm/OnlineSurvey/commit/62dcb649264dd9b5482421f28c0eb0a2679b83b7) 

2019年11月28日 GMT+8 下午9:18	[verifyInvDetails 审核页基本完成功能](https://github.com/suvvm/OnlineSurvey/commit/9f1151146bb21a8b50c4e54a0997d04bdece8c40) 

2019年11月28日 GMT+8 下午9:20	[InvController 添加处理审核请求的方法](https://github.com/suvvm/OnlineSurvey/commit/dc0572a3a85b90161cc3590063e291ceff104897) 

2019年11月28日 GMT+8 下午10:18	[创建并注册答题页面involvedInv](https://github.com/suvvm/OnlineSurvey/commit/deba7894102499700c80c8da710f199d44128bfa) 

2019年11月28日 GMT+8 下午10:26	[involvedInv 完成问题显示与答案存储](https://github.com/suvvm/OnlineSurvey/commit/d167080a01dfae5685033fcaafd1d55f85e9c8e3) 

2019年11月29日 GMT+8 下午8:59	[mUserMapper 添加插入用户作答表的方法](https://github.com/suvvm/OnlineSurvey/commit/d9fb0881938754eec23f573fb23a92e58594e407) 

2019年11月29日 GMT+8 下午9:01	[消除部分warning](https://github.com/suvvm/OnlineSurvey/commit/5fc4a64b3a38cc77204aaa8bf7233e3db52565bc) 

2019年11月29日 GMT+8 下午9:01	[involvedInv 基本完成用户作答功能](https://github.com/suvvm/OnlineSurvey/commit/4fce7a05f355ce52a83e24b409f6c463ca0c8b54) 

2019年11月29日 GMT+8 下午9:28	[重命名suvvm头像文件avatar.jpg为avatar_suvvm.jpg](https://github.com/suvvm/OnlineSurvey/commit/b4720eb34de14e0ae4a10b278c95b32d2eeb830e) 

2019年11月29日 GMT+8 下午9:29	[重命名suvvm人脸识别文件face.jpg为face_suvvm.jpg](https://github.com/suvvm/OnlineSurvey/commit/9d36d2d0ae4a47334ee2612dde7e8a6241b73121) 

2019年11月29日 GMT+8 下午9:30	[添加测试用户静态图片文件](https://github.com/suvvm/OnlineSurvey/commit/3a695f3a2d48ae5dd416f24dd9388fe104c9dd9e) 

2019年11月29日 GMT+8 下午9:31	[user 页修改临时图片地址](https://github.com/suvvm/OnlineSurvey/commit/f169dc6f6d298b325cb1f36d006049b7676f7d59) 

2019年11月29日 GMT+8 下午9:33	[InvController 删除无用方法](https://github.com/suvvm/OnlineSurvey/commit/cc7319c4ba865d058be0a33a49cf90154f7b2e42) 

2019年11月29日 GMT+8 下午9:35	[UserController 添加处理插入用户作答请求的方法](https://github.com/suvvm/OnlineSurvey/commit/da2df22bdde20143a8db9c0b1a987a1aaea3120d) 

2019年11月29日 GMT+8 下午10:27	[test 测试由后端取出base64编码在前端显示文件](https://github.com/suvvm/OnlineSurvey/commit/5641367c98b7acded43754494e217e4e2ab90840) 

2019年11月29日 GMT+8 下午10:27	[user 页面图片由临时图片转为后台图片](https://github.com/suvvm/OnlineSurvey/commit/a32ba13d62b185c8bd66e7b15d8de9b22f23f813) 

2019年11月29日 GMT+8 下午10:28	[UserController 添加处理根据id获取用户全部信息的请求的方法](https://github.com/suvvm/OnlineSurvey/commit/0442c31a1d77e28a28d98a96ff6ca1fabbf73142) 