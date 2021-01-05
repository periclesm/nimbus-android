package net.cloudfields.nimbus.platform.networker

class Networker {
    companion object {
        fun getJSONData(config: NetConfig, callback: (NetResponse) -> Unit) {
            config.function = NetConfig.NetworkerFunction.json
            NetAgent.shared.getData(config, callback = { response ->
                callback(response)
            })
        }

        fun getImage(config: NetConfig, callback: (NetResponse) -> Unit) {
            config.function = NetConfig.NetworkerFunction.image
            NetAgent.shared.getData(config, callback = { response ->
                callback(response)
            })
        }

        fun getData(config: NetConfig, callback: (NetResponse) -> Unit) {
            config.function = NetConfig.NetworkerFunction.data
            NetAgent.shared.getData(config, callback = { response ->
                callback(response)
            })
        }
    }
}