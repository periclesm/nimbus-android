package net.cloudfields.nimbus.platform.networker

class Networker {
    companion object {
        fun getJSONData(config: NetConfig, callback: (NetResponse) -> Unit) {
            NetAgent.shared.getData(config, NetConfig.NetworkerFunction.json, callback = { response ->
                callback(response)
            })
        }

        fun getImage(config: NetConfig, callback: (NetResponse) -> Unit) {
            NetAgent.shared.getData(config, NetConfig.NetworkerFunction.image, callback = { response ->
                callback(response)
            })
        }

        fun getData(config: NetConfig, callback: (NetResponse) -> Unit) {
            NetAgent.shared.getData(config, NetConfig.NetworkerFunction.data, callback = { response ->
                callback(response)
            })
        }
    }
}