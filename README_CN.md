### 
# API交易
[English API-docs](https://github.com/BitCola/API-docs)

## 1 说明


####  API 请求服务地址
    
`https://api.bitcola.io`

####  签名方式
`申请 API 将获得 accessKey 和 secretKey，先用sha加密secretKey，然后根据加密过的secretKey把请求的参数签名，请求参数按照ascii值排序加密，通过md5填充16位加密`
### [示例代码](https://github.com/BitCola/API-Doc/blob/master/src/main/java/com/bitcola/doc/api/TradeApi.java)

####  访问限制

`每个接口每秒限制调用 5 次`

### [错误码](https://github.com/BitCola/API-docs/blob/master/README_CN.md#5-错误码)


## 2 配置

```
GET /api/market/config
```
> 返回示例

```json
{
    "timestamp": 1577431352449,
    "status": 200,
    "message": "message.success.default",
    "data": [
        {
            "pair": "EOS_USDT",
            "amountScale": 3,
            "priceScale": 4
        }
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`timestamp`|number|服务器时间|
|`data.pair`|string|交易对|
|`data.amountScale`|number|数量精度|
|`data.priceScale`|number|价格精度|
## 3 市场

    
#### 获取 ticker 数据
```
GET /api/market/ticker
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`pair`|string|是|交易对|ETH_USDT|




> 返回示例

```json
{
    "timestamp": 1577430942388,
    "status": 200,
    "message": "message.success.default",
    "data": {
        "pair": "ETH_USDT",
        "price": 124.77,
        "vol": 296.6844,
        "change": 0,
        "max24h": 132.89,
        "min24h": 0,
        "amount": 1641020.050747
    }
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.pair`|string|交易对|
|`data.vol`| number |24 小时成交量|
|`data.price`| number |最新成交价|
|`data.change`| number |涨跌幅|
|`data.max24h`| number |24 小时最高价|
|`data.min24h`| number |24 小时最低价|
|`data.amount`| number |24 小时成交额|
#### 所有 ticker 数据
```
GET /api/market/all_ticker
```
> 返回示例

```json
{
    "timestamp": 1577431180793,
    "status": 200,
    "message": "message.success.default",
    "data": [
        {
            "pair": "BCB_USDT",
            "price": 0.05963,
            "vol": 6054.18,
            "change": 0.0131,
            "max24h": 0.05963,
            "min24h": 0.05886,
            "amount": 371.5105867
        }
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.pair`|string|交易对|
|`data.vol`| number |24 小时成交量|
|`data.price`| number |最新成交价|
|`data.change`| number |涨跌幅|
|`data.max24h`| number |24 小时最高价|
|`data.min24h`| number |24 小时最低价|
|`data.amount`| number |24 小时成交额|
#### 获取深度
```
GET /api/market/depth
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`pair`|string|是|交易对|ETH_USDT|
|`size`|integer|否|长度，默认 100，最大 100|2|

> 返回示例

```json
{
    "timestamp": 1577431217893,
    "status": 200,
    "message": "message.success.default",
    "data": {
        "ask": [
            [
                7193.31,
                0.1367
            ],
            [
                7194.61,
                0.1576
            ]
        ],
        "bids": [
            [
                7187.79,
                0.1252
            ],
            [
                7187.59,
                0.0461
            ]
        ]
    }
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.ask`|array|卖方深度[价格,数量]|
|`data.bids`|array|买方深度[价格,数量]|
#### 最新成交
```
GET /api/market/trades
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`pair`|string|是|交易对|ETH_USDT|
|`size`|integer|否|返回最新条数，默认20，最大 100|2|

> 返回示例

```json
{
    "timestamp": 1577431243818,
    "status": 200,
    "message": "message.success.default",
    "data": [
        {
            "id": "49dee280-c000-498b-9797-bfb3c51df93d",
            "price": 7191.62,
            "number": 0.0029,
            "direction": "BUY",
            "timestamp": 1577431222411
        },
        {
            "id": "7928b4b1-9aaf-45e8-94c2-424a93d3fbba",
            "price": 7191.35,
            "number": 0.0073,
            "direction": "SELL",
            "timestamp": 1577431206029
        }
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.id`|string|随机UUID|
|`data.price`|number|价格|
|`data.number`|number|数量|
|`data.direction`|string|方向|
|`data.timestamp`|number|成交时间|
#### k 线
```
GET /api/market/kline
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`pair`|string|是|交易对|ETH_USDT|
|`type`|string|是|1m/5m/15m/30m/1h/4h/6h/8h/12h/1d|1m|
|`endTimestamp`|number|否|k 线截止时间，默认为服务器当前时间|1561384440000|
|`size`|integer|否|返回数据长度，默认 500，最大 1440|1|
> 返回示例

```json
{
    "timestamp": 1577431369928,
    "status": 200,
    "message": "message.success.default",
    "data": [
        [
            1577431080000,
            7188.89,
            7191.3,
            7188.17,
            7188.17,
            0.032,
            230.096033
        ]
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data`|array|[时间戳,开,高,低,收,成交量,成交额]|
## 4 交易

    
#### 委托下单
```
GET /api/trade/order
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accessKey`|string|是|accessKey|accessKey|
|`amount`|number|是|数量|99|
|`direction`|string|是|方向(BUY/SELL)|BUY|
|`pair`|string|是|交易对|BCB_USDT|
|`price`|number|是|价格|100|
|`reqTime`|number|是|请求时间（毫秒）|1561695795677|
|`type`|string|是|下单类型，目前只支持 LIMIT|LIMIT|
|`sign`|string|是|签名|8fef38cf548c3c639eff92914acf6b80|




> 返回示例

```json
{
    "timestamp": 1577434669755,
    "status": 200,
    "message": "message.success.default",
    "data": "1210474828318928896"
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data`|string|订单 ID|
#### 取消委托
```
GET /api/trade/cancelOrder
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accessKey`|string|是||accessKey|
|`id`|string|是|订单 ID|1210474828318928896|
|`reqTime`|number|是||1561696191547|
|`sign`|string|是||98beddad85269b6f79bda169705e1abc|





#### 获取订单
```
GET /api/trade/getOrder
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accessKey`|string|是||accessKey|
|`id`|string|是|订单 ID|1144462404485959680|
|`reqTime`|number|是||1561696726670|
|`sign`|string|是||8ce4d660ce738d8046ea20f4570eb340|




> 返回示例

```json
{
    "timestamp": 1577434987393,
    "status": 200,
    "message": "message.success.default",
    "data": {
        "orderId": "1210474828318928896",
        "pair": "BCB_USDT",
        "direction": "BUY",
        "userId": 200147,
        "timestamp": 1577434663263,
        "price": 0.06,
        "number": 100,
        "remain": 100,
        "status": "PENDING",
        "type": "LIMIT",
        "averagePrice": 0,
        "feeRate": 0.002
    }
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.orderId`|string|订单ID|
|`data.pair`|string|交易对|
|`data.direction`|string|下单方向|
|`data.timestamp`|number|下单时间戳|
|`data.price`|number|下单价格|
|`data.number`|number|下单数量|
|`data.remain`|number|未成交数量|
|`data.status`|string|订单状态|
|`data.type`|string|订单类型|
#### 获取账户信息
```
GET /api/trade/getAccountInfo
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accesskey`|string|是||accessKey|
|`reqTime`|number|是||1561694265429|
|`sign`|string|是||9fa4448b77f3905754e58971797cc4db|




> 返回示例

```json
{
    "timestamp": 1577434987393,
    "status": 200,
    "message": "message.success.default",
    "data": {
        "balances": [
            {
                "coinCode": "USDT",
                "available": 40.0177,
                "frozen": 0
            }
        ]
    }
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.balances.coinCode`|string|币种名称|
|`data.balances`|array|账户信息|
|`data.balances.available`|number|可用资金|
|`data.balances.frozen`|number|冻结资金|
#### 获取订单列表
```
GET /api/trade/getOrders
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accessKey`|string|是||accessKey|
|`direction`|string|否|下单方向(BUY/SELL)|BUY|
|`page`|number|否|页数|默认1|
|`limit`|number|否|每页条数|默认10，最大100|
|`pair`|string|是|交易对| BTC_USDT|
|`reqTime`|number|是||1561696726670|
|`status`|string|否|订单状态|多种状态逗号分隔，例如：PENDING,<br>PARTIAL_COMPLETED,PARTIAL_CANCELLED,<br>FULL_CANCELLED,FULL_COMPLETED|
|`type`|string|否|订单类型|LIMIT|
|`sign`|string|是||8ce4d660ce738d8046ea20f4570eb340|




> 返回示例

```json
{
    "timestamp": 1577434987393,
    "status": 200,
    "message": "message.success.default",
    "data": [
        {
            "orderId": "1210474828318928896",
            "pair": "BCB_USDT",
            "direction": "BUY",
            "userId": 200147,
            "timestamp": 1577434663263,
            "price": 0.06,
            "number": 100,
            "remain": 100,
            "status": "PENDING",
            "type": "LIMIT",
            "averagePrice": 0,
            "feeRate": 0.002
        }
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.id`|string|订单 ID|
|`data.pair`|string|交易对|
|`data.direction`|string|下单方向|
|`data.timestamp`|number|下单时间戳|
|`data.price`|number|下单价格|
|`data.number`|number|下单数量|
|`data.remain`|number|未成交数量|
|`data.status`|string|订单状态|
|`data.type`|string|订单类型|

#### 获取订单成交记录
```
GET /api/trade/getMatchRecord
```

>Query
    
|参数名|类型|必需|描述|示例|
|---------|-------|-------------|-------|---|
|`accessKey`|string|是||accessKey|
|`id`|string|否|订单id||
|`direction`|string|否|下单方向|BUY|
|`pair`|string|否|交易对| BTC_USDT|
|`page`|number|否|页数|默认1|
|`limit`|number|否|每页条数|默认10，最大100|
|`reqTime`|number|是||1561696726670|
|`sign`|string|是||8ce4d660ce738d8046ea20f4570eb340|




> 返回示例
```json
{
    "timestamp": 1577434987393,
    "status": 200,
    "message": "message.success.default",
    "data": [
        {
            "orderId": "1144462404485959680",
            "pair": "BTC_USDT",
            "direction": "SELL",
            "timestamp": 1561696082293,
            "price": 6379,
            "number": 0.1,
            "type": "TACKER",
            "fee": 0.0002,
            "feeCoinCode":"USDT",
            "userId": 200000
        }
    ]
}
```
> 返回参数
    
|参数名|类型|描述|
|--|--|--|
|`data.orderId`|string|订单 ID|
|`data.pair`|string|交易对|
|`data.direction`|string|下单方向|
|`data.timestamp`|number|时间戳|
|`data.price`|number|成交价格|
|`data.number`|number|成交数量|
|`data.fee`|number|手续费|
|`data.feeCoinCode`|string|手续费币种|
|`data.userId`|string|用户 ID|
|`data.type`|string|订单类型|

## 5 错误码

|status|message|错误信息|
|-|-|-|
|200|Success|成功|
|1001|Tip|错误提示|
|500|Error|错误|
|1003|Verification Failed|签名错误|
|1004|In Maintenance|接口维护中|
|1005|Not Allow Trade|当前交易对不允许交易|
|1006|The transaction has not been opened yet|当前交易对暂未开放交易|
|1007|Price error|输入错误的价格|
|1008|Amount error|输入错误的数量|
|2001|Insufficient balance or Parameter 'pair' error|余额不足或交易对参数错误|
|3005|Parameter error|传入参数错误|
|3006|Ip error|不是申请API时填入的IP地址|
|3007|Expire|请求已过期|



