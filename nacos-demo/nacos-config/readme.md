通过 Nacos 的 @NacosValue 注解设置属性值。
@Controller
@RequestMapping("config")
public class ConfigController {

    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public boolean get() {
        return useLocalCache;
    }
}
启动 NacosConfigApplication，调用 curl http://10.1.22.20:8080/config/get，返回内容是 false。

通过调用 Nacos Open API 向 Nacos server 发布配置：dataId 为example，内容为useLocalCache=true

curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=example&group=DEFAULT_GROUP&content=useLocalCache=true"
再次访问 http://10.1.22.20:8080/config/get，此时返回内容为true，说明程序中的useLocalCache值已经被动态更新了。