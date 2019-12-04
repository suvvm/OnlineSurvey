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

2019年11月30日 GMT+8 上午11:34	[user 页更新用户判断机制](https://github.com/suvvm/OnlineSurvey/commit/b43387a5cf5b8851280c626b14ff053146997832)

2019年11月30日 GMT+8 下午6:42	[CmpController 添加处理更新公司信息请求的方法](https://github.com/suvvm/OnlineSurvey/commit/a4ac01c745afbffefad147aa5ac394e4b76c45e0)

2019年11月30日 GMT+8 下午6:43	[创建并完成公司管理页](https://github.com/suvvm/OnlineSurvey/commit/adf075bbf371c63e5d6543a0811e498e3999ddde)

2019年11月30日 GMT+8 下午6:44	[创建公司用户管理调查页](https://github.com/suvvm/OnlineSurvey/commit/004b0b054e8503f699b90aeb0cb2ca7de50b6f1f)

2019年11月30日 GMT+8 下午6:45	[创建调查修改页](https://github.com/suvvm/OnlineSurvey/commit/c8ad3517357c2c5b1359aa52ac3c53889d3165a0)

2019年11月30日 GMT+8 下午6:47	[注册allCmpInv mdfInv userCompany至pages.json](https://github.com/suvvm/OnlineSurvey/commit/2da64edc41d527733641409330c25e70f644cc81)

2019年11月30日 GMT+8 下午6:48	[user 用户页完善具体内容](https://github.com/suvvm/OnlineSurvey/commit/8bb6df134585a204d7ea61854196f9c5bc59ec7c)

2019年11月30日 GMT+8 下午8:43	[InvMapper 修正update动态SQL生成方法中错误的表名](https://github.com/suvvm/OnlineSurvey/commit/7969c1fe8f030fd8c7bce148e6228fc0197cbc08)

2019年11月30日 GMT+8 下午8:43	[InvController 添加处理修改问卷请求的方法](https://github.com/suvvm/OnlineSurvey/commit/25dbbb022171c9b1d63ac232a4a7814d06a46738)

2019年11月30日 GMT+8 下午8:45	[测试分页方法](https://github.com/suvvm/OnlineSurvey/commit/f982bf830e3c572ac20b4469cb16e9e02d90e3f1)

2019年11月30日 GMT+8 下午8:46	[mallCmpInv 功能基本完成](https://github.com/suvvm/OnlineSurvey/commit/8eac250b282d0556754f68ddc699629404930236)

2019年11月30日 GMT+8 下午8:46	[添加分页功能](https://github.com/suvvm/OnlineSurvey/commit/015fd8df12e1118e0121a0cef04f3f1d1c397f21)

2019年11月30日 GMT+8 下午8:47	[mdfInv 功能基本完成](https://github.com/suvvm/OnlineSurvey/commit/37e703b58684577e2f0d1400819796730cdeea44)

2019年11月30日 GMT+8 下午8:50	[user 页添加登出按钮](https://github.com/suvvm/OnlineSurvey/commit/809f73c51789d1fb063c200db63be312c9711ebe)

2019年11月30日 GMT+8 下午9:55	[UserController 添加处理更新用户请求的方法](https://github.com/suvvm/OnlineSurvey/commit/b7f92c2c0c14eb275a51df707974672446cec27b)

2019年11月30日 GMT+8 下午9:56	[创建注册并完成用户更新页](https://github.com/suvvm/OnlineSurvey/commit/2b2d9fb33e21ff9fc371e4e5292ba2ed7c613251)

2019年11月30日 GMT+8 下午10:05	[新增关于我们页](https://github.com/suvvm/OnlineSurvey/commit/9c1211993dc84d3014c1be7d68f4f132c3028258)

2019年11月30日 GMT+8 下午10:05	[user 页更新部分路由](https://github.com/suvvm/OnlineSurvey/commit/19db8b7f0f06803ecd249cba7453c6c859ea0b40)

2019年11月30日 GMT+8 下午10:14	[完成关于页面](https://github.com/suvvm/OnlineSurvey/commit/ef06d8ab70979f6fb7a25a53a0da71a7bc7675f6)

2019年11月30日 GMT+8 下午11:26	[两登录页添加加载时判断登录状态跳转](https://github.com/suvvm/OnlineSurvey/commit/0317d8e11a64f8fa3c32950696d532865c64a2fa)

2019年11月30日 GMT+8 下午11:27	[user 添加登出方法](https://github.com/suvvm/OnlineSurvey/commit/63cd08f35b537b7f2f7d7a926a04dc4139fd8d83)

2019年12月1日 GMT+8 下午7:15	[UserController 修改插入用户返回数据](https://github.com/suvvm/OnlineSurvey/commit/22b01199bffc272e9d8e0e398e07d94417f3e344)

2019年12月1日 GMT+8 下午7:15	[user 添加部分路由](https://github.com/suvvm/OnlineSurvey/commit/b0669f1f3ad34d177ee36c358bc052f5359e76b4)

2019年12月1日 GMT+8 下午7:17	[verifyInvestigates 修正错误的分页方法](https://github.com/suvvm/OnlineSurvey/commit/9adf63c627ca5b13167bb6757e2cf9a2bcfd7053)

2019年12月1日 GMT+8 下午7:17	[registerCompany 修改radio数据格式](https://github.com/suvvm/OnlineSurvey/commit/62bd8465de064df3da08da08b8d0f7553729a9db)

2019年12月1日 GMT+8 下午7:20	[重命名rergisterCompany为registerCompany](https://github.com/suvvm/OnlineSurvey/commit/2c380001a9776ea3d01693600618a498571a1ecb)

2019年12月1日 GMT+8 下午7:22	[添加测试用户静态头像与识别文件](https://github.com/suvvm/OnlineSurvey/commit/77d0f0b5b08eefb0065b916e8a10338991c136ec)

2019年12月1日 GMT+8 下午7:24	[创建分类索引页categories](https://github.com/suvvm/OnlineSurvey/commit/05fcaf93dee645f62d529de7290100cd5f319809)

2019年12月1日 GMT+8 下午7:25	[创建home页](https://github.com/suvvm/OnlineSurvey/commit/1c5090296be0c2c53aac406249c0a9512668e925)

2019年12月1日 GMT+8 下午7:25	[创建搜索页](https://github.com/suvvm/OnlineSurvey/commit/a335bd6798e133da13c82c6350e5bda8f82f92b6)

2019年12月1日 GMT+8 下午7:26	[将home search categories页注册至pages.json](https://github.com/suvvm/OnlineSurvey/commit/dae6cbc1658c393bc0ba777ac0984083a620927c)

2019年12月1日 GMT+8 下午9:25	[完成tag与investigate多对多映射](https://github.com/suvvm/OnlineSurvey/commit/4955b5725747213af37c517bfcca14ca4157fb57)

2019年12月1日 GMT+8 下午9:27	[TagController 添加处理推荐请求的方法](https://github.com/suvvm/OnlineSurvey/commit/50b7ff0e5b6710462801ffdd83a185dcf6a6372d)

2019年12月1日 GMT+8 下午9:28	[部分完善home页](https://github.com/suvvm/OnlineSurvey/commit/6fc1fa54044ab6b5395e9809f8a62833d7da386a)

2019年12月1日 GMT+8 下午9:28	[添加首页滚动图片组资源](https://github.com/suvvm/OnlineSurvey/commit/338f88f3c4b9f8cd706d1d596432c137f948f7cf)

2019年12月1日 GMT+8 下午10:13	[新建并注册页面todayInv](https://github.com/suvvm/OnlineSurvey/commit/800f100620af107f35d22df067068016f8df26f4)

2019年12月1日 GMT+8 下午10:14	[TagController 推荐请求添加去重方法](https://github.com/suvvm/OnlineSurvey/commit/3fdd172d4b0420db844d375b7b8d3057b9c4c618)

2019年12月1日 GMT+8 下午10:15	[InvController 添加处理获取全部可见问卷与获取全部问卷请求的方法](https://github.com/suvvm/OnlineSurvey/commit/69ba32ede825d972b611b4f3801ce487b5cffdbc)

2019年12月1日 GMT+8 下午10:15	[home 完成推荐功能](https://github.com/suvvm/OnlineSurvey/commit/89048679fd5460974e70d980ce848d9acc5c4403)

2019年12月1日 GMT+8 下午10:33	[安装echarts](https://github.com/suvvm/OnlineSurvey/commit/9f03bc15857e363f1b5777b4d11ee2b9209ac5b9)

2019年12月2日 GMT+8 下午10:14	[添加回答持久化类与mapper 建立用户与回答 问卷与回答的一对多映射](https://github.com/suvvm/OnlineSurvey/commit/e364215d838b08f75f5cff374c0a23c01def6a3d)

2019年12月2日 GMT+8 下午10:15	[InvController 添加处理通过回答查找问卷请求的方法](https://github.com/suvvm/OnlineSurvey/commit/f1afe5bf492e81727e603e7522469833481192ff)

2019年12月2日 GMT+8 下午10:16	[添加查找问卷的测试方法](https://github.com/suvvm/OnlineSurvey/commit/a085638fe5372766172d69e9432252edca3b465b)

2019年12月2日 GMT+8 下午10:18	[创建并注册全部问卷页 历史页 我的回答页 问卷结果页](https://github.com/suvvm/OnlineSurvey/commit/2e7be63f83873337b5c0f79d3507aab113bf371e)

2019年12月2日 GMT+8 下午10:18	[引用echarts](https://github.com/suvvm/OnlineSurvey/commit/d8a874bf45f50ecd40321dc0dc4b8a54c1b0be0b)

2019年12月2日 GMT+8 下午10:19	[基本完成home页](https://github.com/suvvm/OnlineSurvey/commit/b8d2ce9daba06d52132821fef55b9e76c6862fa2)

2019年12月2日 GMT+8 下午10:19	[基本完成查看今日问卷页](https://github.com/suvvm/OnlineSurvey/commit/1e8792635325468dac3cd53a6fc6af6f829c876a)

2019年12月2日 GMT+8 下午10:20	[involvedInv 修正缩进](https://github.com/suvvm/OnlineSurvey/commit/892374ede8b9facc6d210e4b5963d8db752a28aa)

2019年12月2日 GMT+8 下午10:21	[mdfInv 添加查看结果按钮](https://github.com/suvvm/OnlineSurvey/commit/651707ec63dbfcd13f518192dcdb7913b353e930)

2019年12月2日 GMT+8 下午10:22	[verifyInvestigates 修正部分功能](https://github.com/suvvm/OnlineSurvey/commit/61261301d9ea68f92122daa0ea835568ff85f9af)

2019年12月2日 GMT+8 下午10:22	[完成搜索功能](https://github.com/suvvm/OnlineSurvey/commit/b72913f4b5f8fb021e70cba131c7af48f0db70d6)

2019年12月2日 GMT+8 下午10:23	[测试图表](https://github.com/suvvm/OnlineSurvey/commit/93373c1101bc6578f0a05e5fe992a901c33efdd7)

2019年12月2日 GMT+8 下午10:23	[修改导航栏](https://github.com/suvvm/OnlineSurvey/commit/2e969c0e3e46589c6de26615f4cae7a821a3442f)

2019年12月3日 GMT+8 下午7:54	[更新maven配置文件pox.xml](https://github.com/suvvm/OnlineSurvey/commit/2309b28516b443d4daf48f7001f199e89cbb679a)

2019年12月3日 GMT+8 下午7:56	[CmpController 修正getCompany方法返回值](https://github.com/suvvm/OnlineSurvey/commit/d89eb7c186b3b8622099a0ae1ce09c8b70864d8b)

2019年12月3日 GMT+8 下午7:57	[UserController 密码加密方式改为md5](https://github.com/suvvm/OnlineSurvey/commit/95f2290cca8dbe9604b79d99f75773a9d34e84d5)

2019年12月3日 GMT+8 下午7:58	[application.yml 数据库配置改为服务配置 部署端口配置为8211](https://github.com/suvvm/OnlineSurvey/commit/affefd23f1bb234cc0251fec0cf7ed1cf7f413c3)

2019年12月3日 GMT+8 下午8:00	[重新生成数据库sql文件并重命名为onlineSurvey.sql](https://github.com/suvvm/OnlineSurvey/commit/a28a422d41532f0cfc439fd9e4cd9f786e6eeb78)

2019年12月3日 GMT+8 下午8:02	[更新数据请求连接为服务器相应地址](https://github.com/suvvm/OnlineSurvey/commit/e889c6587994cddaa6148c14ed10c6fa73fbe868)

2019年12月3日 GMT+8 下午8:05	[创建并注册页面allCompany 与allUsers](https://github.com/suvvm/OnlineSurvey/commit/e1f28678ec899113440639537f7fcbd4d9039149)

2019年12月3日 GMT+8 下午8:05	[生成部署文件](https://github.com/suvvm/OnlineSurvey/commit/7172ddc3c453895a6e5b686b676e0233fa6fd586)

2019年12月4日 GMT+8 下午3:43	[UserController 创建处理提升用户权限请求的方法](https://github.com/suvvm/OnlineSurvey/commit/bde13521b65884b2e551d3c9d708fd55f3897387)

2019年12月4日 GMT+8 下午3:43	后台工程打包上传服务器

2019年12月4日 GMT+8 下午3:43	服务器后台功能启动

2019年12月4日 GMT+8 下午3:44	[修正请求地址为后端服务器地址](https://github.com/suvvm/OnlineSurvey/commit/a2330cfedac91c45b4a00ef8c8b69edfeb1644e2)

2019年12月4日 GMT+8 下午3:45	[添加空数据判断](https://github.com/suvvm/OnlineSurvey/commit/30690f235fd79d1ae7c9c119039aa19dda13bff2)

2019年12月4日 GMT+8 下午3:47	[userCompany更新请求地址为后端服务器地址](https://github.com/suvvm/OnlineSurvey/commit/eeb5a62b4f87ebdd34509a729a950f47ecce989f)

2019年12月4日 GMT+8 下午3:48	[allCmpInv添加下拉刷新方法](https://github.com/suvvm/OnlineSurvey/commit/0077fa47abc07ae0c2fc7fa4d2676a58ae516b08)

2019年12月4日 GMT+8 下午3:49	[allUsers添加排序方法](https://github.com/suvvm/OnlineSurvey/commit/ef37a386de7ec43e5f6f6bf8a69a5150c0e22684)

2019年12月4日 GMT+8 下午3:49	[user 管理员可以访问数据监控页](https://github.com/suvvm/OnlineSurvey/commit/2333e7e15f91fb68e4c5e0bcef3c35b3a7ce3d0a)

2019年12月4日 GMT+8 下午3:50	[vue前端打包发布](https://github.com/suvvm/OnlineSurvey/commit/2fad845cd7f7b4d4133eb12a4d3a31f7629892e7)

2019年12月4日 GMT+8 下午3:50	vue前端上传服务器

2019年12月4日 GMT+8 下午3:50	配置服务器相关端口

2019年12月4日 GMT+8 下午3:50	配置nginx

2019年12月4日 GMT+8 下午7:45	[添加webApp打包工程](https://github.com/suvvm/OnlineSurvey/commit/d63b8e257eb4ee85d39ef7ad69b0f7e11fa45aea)

2019年12月4日 GMT+8 下午8:07	[打包为安卓app](https://github.com/suvvm/OnlineSurvey/commit/2d9e8427d47b05e648e7ab1fe8696833f82ea9e9)

2019年12月4日 GMT+8 下午8:07	项目开发基本完成，进入调试阶段

2019年12月4日 GMT+8 下午10:01	[删除计划表](https://github.com/suvvm/OnlineSurvey/commit/103d068ab4ed4e24b685688788831d56b55d3f30)

2019年12月4日 GMT+8 下午10:01	[删除测试Controller](https://github.com/suvvm/OnlineSurvey/commit/11db3c49dc20d32dd233a181e4527a41998751c2)

2019年12月4日 GMT+8 下午10:01	[完善后端注释](https://github.com/suvvm/OnlineSurvey/commit/cd84ad5d12a8780946b79557d8955cff4753be25)

