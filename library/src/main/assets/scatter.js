function isAndroid() {
    var u = navigator.userAgent;
    if (u.indexOf("Android") > -1 || u.indexOf("Linux") > -1) {
         return true;
    }
    return false;
}

function isIOS() {
    var u = navigator.userAgent;
    if (u.indexOf("iPhone") > -1 || u.indexOf("iOS") > -1) {
        return true;
    }
    return false;
}

function _sendPeRequest(callback,params,methodName) {
    var data = {'params': JSON.stringify(params),
                'callback': callback,
                'methodName': methodName
               }
    var json = JSON.stringify(data)
    if (isAndroid()) {
        window.DappJsBridge.pushMessage(json)
    } else if (isIOS()) {
        window.webkit.messageHandlers.pushMessage.postMessage(json)
    }else{
        alert("not supported client")
    }
}

function callbackGenerator() {
    return 'callback' + (new Date().getTime() + parseInt(Math.random() * 100000000000))
}

class App {

    constructor(){
       this.eventHandlers = {}
       this.isExtension = true
    }

    isConnected(){
        return true;
    }

    isPaired(){
        return true;
    }

    disconnect(){
        return true;
    }

    sendApiRequest(request){
        const serialNumber = callbackGenerator();
        _sendPeRequest(serialNumber,request.payload,request.type);
        return new Promise((resolve, reject) => {
            window.callbackResult = function (returnSerialNumber, result) {
               if (returnSerialNumber == serialNumber) {
                  var tmp = JSON.parse(result)
                  if(tmp.isError){
                    reject(tmp.message)
                  }else{
                    resolve(tmp.data)
                  }
               }
            }
        })
    }

    addEventHandler(handler, key){
        if(!key) key = 'app';
	    this.eventHandlers[key] = handler;
    }

    removeEventHandler(key){
	    if(!key) key = 'app';
	    delete this.eventHandlers[key];
    }

    addEventHandler(handler){
        this.eventHandlers["app"] = handler;
    }
}

var uint8ArrayToHex = (fileData) => {
   var dataString = "";
   for (var i = 0; i < fileData.length; i++) {
       if(fileData[i] <= 0xf){
            dataString += "0"
       }
       dataString += fileData[i].toString(16);
   }
   return dataString
}

class Index extends App{
	constructor() {
	    super();
		this.isExtension = true;
		this.identity = null
	}

	useIdentity(identity){
	   return new Promise((resolve,reject) => {
            window.wallet.sendApiRequest({type:"useIdentity",payload:identity})
            .then(res => {
                this.identity = res;
                resolve(res)
            })
       });
	}

	getIdentity(requiredFields) {
		return new Promise((resolve,reject) => {
		    window.wallet.sendApiRequest({type:"getIdentity",payload:requiredFields})
		    .then(res => {
		        this.identity = res;
                resolve(res)
		    })
		});
	}
    getArbitrarySignature(publicKey, data, whatfor = '', isHash = false) {
        let params = {
            publicKey: publicKey,
            data: data,
            whatfor: whatfor,
            isHash: isHash
        };
        return new Promise((resolve,reject) => {
            window.wallet.sendApiRequest({type:"requestArbitrarySignature",payload:params})
            .then(res => {
                this.identity = res;
                   resolve(res)
            })
        });
    }

	getIdentityFromPermissions() {
		return new Promise((resolve,reject) => {
            window.wallet.sendApiRequest({type:"identityFromPermissions",payload:{}})
            .then(res => {
                 this.identity = res;
                resolve(res)
            })
        })
	}
	forgetIdentity() {
		return new Promise((resolve, reject) =>{
			this.identity = null;
			resolve(true)
		})
	}
	authenticate(nonce) {
		return new Promise((resolve,reject) => {
            window.wallet.sendApiRequest({type:"authenticate",payload:nonce})
            .then(res => {
                resolve(res)
            })
        })
	}

    getPublicKey(param) {
         return new Promise((resolve, reject) => {
            window.wallet.sendApiRequest({type:"identityFromPermissions",payload:param})
            .then(res => {
                resolve(res.accounts.filter(x => x.blockchain === 'eos').map(x => x.publicKey))
            }).catch(e => {reject(e)})
        })
    }

    linkAccount(publicKey, network) {
         return new Promise(async (resolve, reject) => {
              window.wallet.sendApiRequest({
                  type:'linkAccount',
                  payload:{network:network,publicKey:publicKey}
              }).then(x => {
                  resolve(x)
              }).catch(x => reject(x))
         })
    }
    hasAccountFor(network) {
        return new Promise(async (resolve, reject) => {
             window.wallet.sendApiRequest({
                 type:'hasAccountFor',
                 payload:{ network:network}
             }).then(x => {
                 resolve(x)
             }).catch(x => reject(x))
        })
    }
    suggestNetwork(network) {
         return new Promise(async (resolve, reject) => {
              window.wallet.sendApiRequest({
                  type:'suggestNetwork',
                  payload:{ network:network}
              }).then(x => {
                  resolve(x)
              }).catch(x => reject(x))
         })
    }
    requestTransfer(network, to, amount, options = {}) {
        console.log(JSON.stringify(network))
        console.log(JSON.stringify(to))
        console.log(JSON.stringify(amount))
        console.log(JSON.stringify(options))
        return new Promise(async (resolve, reject) => {
           window.wallet.sendApiRequest({
              type:'requestTransfer',
              payload:{ network:network, to:to, amount:amount,options:options}
          }).then(x => {
              resolve({signatures:x.signatures,serializedTransaction:signargs.serializedTransaction})
          }).catch(x => reject(x))
       })
    }
    requestSignature(signargs) {
        return new Promise(async (resolve, reject) => {
           window.wallet.sendApiRequest({
              type:'requestSignature',
              payload:{ transaction:signargs, blockchain:"eos", requiredFields:requiredFields }
          }).then(x => {
              resolve({signatures:x.signatures,serializedTransaction:signargs.serializedTransaction})
          }).catch(x => reject(x))
       })
    }
    createTransaction(blockchain, actions, account, network) {
        console.log('createTransaction')
        return 0
    }

    transact(t,e){
         console.log('transact')
         return 0
    }

	eos (network, Eos, options) {
        console.log("network:", network)
        console.log("Eos:", Eos)
        console.log("options:",options || {})
        var beta3 = options && options.beta3 || !1
        debugger;
        if (beta3) {
            class AuthorityProvider {
                getRequiredKeys = (param) => {
                    return new Promise((resolve, reject) => {
                        window.wallet.sendApiRequest({type:"identityFromPermissions",payload:param})
                        .then(res => {
                            resolve(res.accounts.filter(x => x.blockchain === 'eos').map(x => x.publicKey))
                        }).catch(e => {reject(e)})
                    })
                };
            }
            class SignatureProvider {
                getAvailableKeys = () => {
                    return new Promise((resolve, reject) => {
                        window.wallet.sendApiRequest({type:"identityFromPermissions",payload:network})
                        .then(res => {
                            resolve(res.accounts.map(x => x.publicKey))
                        }).catch(e => {reject(e)})
                    })
                }

                uint8ArrayToString = (fileData) =>{
                     var dataString = "";
                     for (var i = 0; i < fileData.length; i++) {
                       dataString += String.fromCharCode(fileData[i]);
                     }
                     return dataString
                }
                sign = (signargs) => {
                    const requiredFields = {};
                    if(signargs.serializedTransaction){
                        console.log(signargs.serializedTransaction)
                        signargs.serializedTransaction = uint8ArrayToHex(signargs.serializedTransaction)
                    }
                    return new Promise(async (resolve, reject) => {
                        window.wallet.sendApiRequest({
                           type:'requestSignature',
                           payload:{ transaction:signargs, blockchain:"eos", network, requiredFields:requiredFields }
                       }).then(x => {
                           resolve({signatures:x.signatures,serializedTransaction:signargs.serializedTransaction})
                       }).catch(x => reject(x))
                   })
               }
            }
            return new Eos({
                rpc: options.rpc,
                authorityProvider: new AuthorityProvider(),
                signatureProvider: new SignatureProvider(),
                textDecoder: new TextDecoder(),
                textEncoder: new TextEncoder()
            })
        } else {
            var endpoint = ""
            if (network.protocol) {
                endpoint += network.protocol
            } else {
                //如果没有协议，默认给他妈的一个http
                endpoint += "http"
            }
            if (!endpoint.endsWith("://")) {
                //判断有没有脑壳抽的前端在协议后面传://
                endpoint += "://"
            }
            if (network.host) {
                endpoint += network.host
            } else {
                //如果不传，只能弹窗提示了，根本他妈的不晓得他要用哪个节点
                alert("Scatter不可用，因为DApp开发者没有按照协议传入host。（Scatter is not available because the DApp developer did not pass in the host according to the protocol.）")
            }
            if (!endpoint.endsWith(":")) {
                //不晓得有没得神经病这个样子传参
                endpoint += ":"
            }
            if (network.port) {
                endpoint += network.port
            }
            var config = {
                httpEndpoint: endpoint,
                chainId: network.chainId,
                expireInSeconds: 60,
                sign: true
            };
            try {
                if (this.identity && this.identity.accounts && this.identity.accounts.length > 0) {
                    config.authorization = this.identity.accounts[0].name + "@" + this.identity.accounts[0].authority;
                }
            } catch (e) {
            }
            config.signProvider = function (param) {
                console.log("requestSignature:" + JSON.stringify(param));
                return new Promise((resolve,reject) => {
                    window.wallet.sendApiRequest({type:"requestSignature",payload:param})
                    .then(res => {
                        resolve(res.signatures[0])
                    }).catch(e => {reject(e)})
                })
            };

            if (config.httpEndpoint.endsWith(":")) {
                config.httpEndpoint = config.httpEndpoint.substring(0, config.httpEndpoint.length - 1);
            }
            if (config.httpEndpoint.startsWith("://")) {
                config.httpEndpoint = config.httpEndpoint.substring(4, config.httpEndpoint.length);
            }
            return Eos(config);
        }
    }
}


function inject() {
    var cacheHandler = {}
    window.wallet = new App();
    window.scatter = new Index();
    document.dispatchEvent(new CustomEvent('scatterLoaded'))
}
inject();
console.log("注入成功...")