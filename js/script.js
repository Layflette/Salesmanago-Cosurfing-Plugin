var COSURFING_NET_PLUGIN = COSURFING_NET_PLUGIN || {};
COSURFING_NET_PLUGIN.newContact =
{
    setName: function (param1) {
        this.name = param1;
    },
    setEmail: function (param1) {
        this.email = param1;
    },
    setFax: function (param1) {
        this.fax = param1;
    },
    setCompany: function (param1) {
        this.company = param1;
    },
    setTags: function (param1) {
        this.tags = param1;
    },
    setState: function (param1) {
        this.state = param1;
    },

    createSendTxt: function()
    {
        var tmp = "USER;" + this.name + ';' + this.email + ';' + this.fax + ';' + this.company + ';' + this.tags + ';' + this.state;
        return tmp;
    },
    sendNewContact: function () {
        var sendTxt = this.createSendTxt();
        document.write(sendTxt);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8082/plugin/run', true);
        xhr.onload = function (e) {
            if (this.status == 200)
                document.write(this.responseText);
        };
        xhr.send(sendTxt);
        xhr.close();
    }
}



COSURFING_NET_PLUGIN.admin =
{
    setOwnerEmail : function(email)
    {
        this.ownerEmail = email;
    },

    setClientId : function(clientId)
    {
        this.clientId = clientId;
    },

    setApiSecret : function(apiSecret)
    {
        this.apiSecret = apiSecret;
    },

    setEndPoint : function (url)
    {
        this.endPoint = url;
    },

    createAuthorisationRequest : function()
    {
        var tmpp = "ADMIN;" + this.ownerEmail + ';' + this.clientId + ';' + this.apiSecret + ';' + this.endPoint + ';';
        return tmpp;
    },

    sendAuthorisationRequest : function()
    {
        var sendTxta = this.createAuthorisationRequest();
        document.writeln(sendTxta);
        var xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:8082/plugin/run', true);
        xhr.onload = function (e) {
            if (this.status == 200)
                document.write(this.responseText);
        };
        xhr.send(sendTxta);
        xhr.close();
    }
}

COSURFING_NET_PLUGIN.admin.setClientId("eeaztded5f45hfn0");
COSURFING_NET_PLUGIN.admin.setApiSecret("dfhic8sohbeie1smkn5kwh7a692vpbvo");
COSURFING_NET_PLUGIN.admin.setOwnerEmail("info@cosurfing.pl");
COSURFING_NET_PLUGIN.admin.setEndPoint("http://app3.salesmanago.pl/api");
COSURFING_NET_PLUGIN.admin.sendAuthorisationRequest();

COSURFING_NET_PLUGIN.newContact.setEmail("pasha@cs.pl");
COSURFING_NET_PLUGIN.newContact.setName("pasha");
COSURFING_NET_PLUGIN.newContact.sendNewContact();

