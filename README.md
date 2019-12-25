# distributed--framework

课程介绍：
    该课程以实战方式实现一套经典的分布式系统架构；
    讲解如何进行系统拆分架构：
    
        传统ssm框架搭建、
        独立restful服务工程搭建、
        服务接口底层访问、
        redis实现业务缓存、
        SSO单点登录系统实现
    
课程规划：
    一、整体介绍
    二、规划工程结构、使用maven进行构建
    三、分布式框架搭建-SSM工程搭建
    四、分布式工程搭建-SSM的测试
    五、Restful原理分析和服务工程搭建
    六、Restful服务发布
    七、Restful服务测试
    八、使用HttpClient实现系统之间服务调用
    九、业务功能缓存的实现-redis单机版安装
    十、业务功能缓存的实现-redis集群环境搭建
    十一、redis单机和集群环境环境测试
    十二、spring和redis的集成
    十三、使用redis实现业务缓存
    十四、单点登录SSO原理分析
    十五、单点登录SSO工程搭建
    十六、单点登录SSO的服务发布
    十七、单点登录SSO的注册功能实现
    十八、单点登录SSO的登录功能实现
    二十、业务系统与单点登录系统的整合
    
    技术选型（主要技术）：
        Spring、SpringMVC、Mybatis
        JSP、jQuery
        httpclient（调用系统服务）
        Mysql
        redis
        
  Cookie的共享：
    A、设置domain：比如 www.abc.com   sso.abc.com
       需要设置domain为：.abc.com
    C、设置path： /
    像我们的demo都是localhost，则不需要设置domain，设置path为 / 即可
    
    在portal-web这个系统index.jsp页面中添加读取cookie的操作，页面加载时去读取cookie，并显示相应的用户信息。
    
    登陆成功后，生成token，并添加cookie
    然后跳转到portal首页，portal的index页面取出cookie中的token进行再次请求，根据返回的数据获取username并显示
    登出后，跳转到portal首页
    
    #目前我们已经实现了单点登录的登陆，其他系统也采用这种方式来获取token，为了更好地体现这个用户是否已经登录，我们来定制一些页面 
    拦截url进行强制登录：
        当用户访问"个人中心"页面的时候，进行url拦截，并强制登录。
        
    
   首先访问portal首页，正常：
    http://localhost:8083/index（portal登录时跳转到sso登录页面进行登录，登陆成功后跳转到portal首页）
    然后访问需要登录页面，localhost:8083/my/mypage
    跳转到单点登录页面：
    http://localhost:8085/sso/login?redirect=http://localhost:8083/my/mypages
    登录成功后跳转到之前的页面：http://localhost:8083/my/mypage
    
    注意：目前只演示了把portal-web加入到单点登录系统中来进行统一登录，其他系统参考上述方法进行改造。
        
    