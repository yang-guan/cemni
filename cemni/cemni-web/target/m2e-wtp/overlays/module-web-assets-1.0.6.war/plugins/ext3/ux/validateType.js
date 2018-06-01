/**
 * @author wz
验证参数类型：



isalpha：是否是字母，isExist：是否存在，password：两次密码是否相同，ischinese：必须中文，isage:0<年龄<150,
isurl:必须是url, max:最大值，min:最小值, isdatecn:日期格式 yyyy-mm-dd,isinteger:为整型，
minlength:最小长度,maxlength:最大长度，isip:为ip格式，isphoto：电话格式为 0598-6398022 或 6398015
ismobilephone：手机号码格式，ismoney：金额 00.00 ,isfloat:浮点数 12.00 ,iscode:邮编（必须6位）
comparesTime:（date12 equalTo:date1）日期二必须大于日期一,iskeycode://只允许数字\字母\下划线\减号
isusername:只允许数字\字母\下划线\减号并且中间必须有一个@符号,ismail:电子邮件格式,
isCardNo:身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X

使用说明：1.使用多重验证时，需要引入  vtype:"mIdentify",validator:"",validatorText:"" 这几个参数，
 validator 是指定验证参数类型，validatorText 是验证参数文本，类型和文本一一对应，每项类型和文本用"|"
分隔，



           例如：    vtype:"mIdentify",
                    validator:"maxlength|ismoney",
                    validatorText:"最长6位|请输入正确的金额"

         2.单项验证时并想自己定义文本时，也是如上写法，只写单一类型对应单一文本就行，



         3.如果是单项验证时只要指定 vtype:""就行，



                      例如： vtype:"ismoney"

 其次这个验证框扩展了ext 的原先的验证形式可以一起混合使用。



//在IE8+和FF下，使用equalTo验证两个相等时，必须使用id，否则将会报错----------------------------
**/
//同步请求
function isblank(val,field){
     if(!field.allowBlank){
                if (/^[ ]+$/.test(val))
                return false;
             }
}
Ext.apply(Ext.form.VTypes,
{

 //首先定义一个vtype名称，和他的验证函数，val参数是文本框的值，field是文本框。一般我就使用val和正则表达式比较就OK了。



 //然后定义一个vtype的报错信息，与vtype名称加Text后缀。OK了。



mIdentify: function(val, field) {
   var values = (field.validator).split("|");
   var texts =(field.validatorText).split("|");

   var sfieldname={};
   for(i=0;i<values.length;i++){
       sfieldname[values[i]]=texts[i];
   }
   isblank(val,field);
    if(values.indexOf("isalpha")>-1){//是否是字母



          try
        {
            if(!/^[a-zA-Z]+$/.test(val)){
                field.invalidText=sfieldname["isalpha"];
                return false;
            }
        }
        catch(e)
        {
            return false;
        }
    }

    if(values.indexOf("isExist")>-1){//是否存在
     //alert(Ext.lib.Ajax.getConnectionObject);
//    var conn = Ext.lib.Ajax.getConnectionObject().conn;
//    conn.open("GET", field.isExistUrl+val,false);
//    conn.send(null);
//    //alert(conn.responseText);
//    var json = Ext.decode(conn.responseText);
//    if(!json.success){
//         field.invalidText= sfieldname["isExist"];
//         return false;
//    }
    var Synchronize = function(url) {
        function createXhrObject() {
            var http;
            var activeX = ['MSXML2.XMLHTTP.3.0', 'MSXML2.XMLHTTP', 'Microsoft.XMLHTTP'];

            try {
                http = new XMLHttpRequest();
            } catch (e) {
                for (var i = 0; i < activeX.length; ++i) {
                    try {
                        http = new ActiveXObject(activeX[i]);
                        break;
                    } catch (e) { }
                }
            } finally {
                return http;
            }
        };

        var conn = createXhrObject();
        conn.open("post", url, false);
        conn.send(null);
        if (conn.responseText != '') {
            var json = (Ext.decode(conn.responseText));
            field.invalidText= sfieldname["isExist"];
            //alert(json.success);
            if(json.success===false){//验证名称存在时为false;
              return false;
            }
        }
        return true;
    };

      
      var url =field.isExistUrl;
      var existFn=field.existFn;
      if(Ext.isFunction(existFn)){
          var a = existFn(field);
          if(!Ext.isEmpty(a)){
              url+=a;
          }
      }else{
          url+=val;
      }
      if(!Synchronize(encodeURI(url))){
          return false;//验证名称存在时为返回false;不存在时就不要返回值;
      }
   }

if(values.indexOf("password")>-1){ //两次密码是否相同
    var pwd = Ext.get(field.equalTo);
    if(val != pwd.getValue()){
        field.invalidText= sfieldname["password"];
        return false;
    }
 }

if(values.indexOf("comparesTime")>-1){ //两次密码是否相同
    var compdate = Ext.get(field.equalTo);
    var compdateValue = compdate.getValue();
    var value1 = Date.parse(compdateValue.replace(/-/g,"/"));
    var value2 = Date.parse(val.replace(/-/g,"/"));
    if(value1 >= value2){
        field.invalidText= sfieldname["comparesTime"];
        return false;
    }
 }
if(values.indexOf("ischinese")>-1){ //必须中文
    var reg = /^[\u4e00-\u9fa5]+$/i;
    if(!reg.test(val))
    {
        field.invalidText= sfieldname["ischinese"];
        return false;
    }
    //return true;
 }
if(values.indexOf("iskeycode")>-1){ //必须中文
    var reg = /^[a-zA-Z0-9][a-zA-Z0-9_-]*$/;
    if(!reg.test(val))
    {
        field.invalidText= sfieldname["iskeycode"];
        return false;
    }
    //return true;
 }
 if(values.indexOf("isage")>-1){ //0<年龄<150
     try
        {
            if(!(parseInt(val) >= 0 && parseInt(val) <= 150)){
                  field.invalidText= sfieldname["isage"];
                  return false;
            }

        }
        catch(err)
        {
            return false;
        }
 }

  if(values.indexOf("isurl")>-1){ //必须是url
    try
        {
            if(!/^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(val)){
                 field.invalidText= sfieldname["isurl"];
                 return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }

  if(values.indexOf("isdatecn")>-1){ //请使用这样的日期格式: yyyy-mm-dd. 例如:2008-06-20.
        try
        {
            var regex = /^(\d{4})-(\d{2})-(\d{2})$/;
            field.invalidText= sfieldname["isdatecn"];
            if(!regex.test(val)) return false;
            var d = new Date(val.replace(regex, '$1/$2/$3'));
            return (parseInt(RegExp.$2, 10) == (1+d.getMonth())) && (parseInt(RegExp.$3, 10) == d.getDate())&&(parseInt(RegExp.$1, 10) == d.getFullYear());
        }
        catch(e)
        {
            return false;
        }
 }

  if(values.indexOf("isinteger")>-1){ //必须为整型






       try
        {
            if(!/^[-+]?[\d]+$/.test(val)){
                field.invalidText= sfieldname["isinteger"];
                return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }

 if(values.indexOf("isfloat")>-1){ //必须为整型






       try
        {
            if(!/^(\d*\.)?\d+$/.test(val)){
                field.invalidText= sfieldname["isfloat"];
                return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }


if(values.indexOf("isip")>-1){ //ip
        try
        {
            if(!(/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(val))){
               field.invalidText= sfieldname["isip"];
               return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }

if(values.indexOf("isphoto")>-1){ //请输入正确的电话号码,如:0920-29392929
        try
        {
            if(!/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/.test(val)){
                field.invalidText= sfieldname["isphoto"];
                return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }

if(values.indexOf("ismobilephone")>-1){ //手机格式
         try
        {
            if(!/(^0?[1][35][0-9]{9}$)/.test(val)){
                field.invalidText= sfieldname["ismobilephone"];
                return false;
            }


        }
        catch(e)
        {
            return false;
        }
 }
 if(values.indexOf("iscode")>-1){ //手机格式
         try
        {
            if(!/^[0-9]{6}$/.test(val)){
                field.invalidText= sfieldname["iscode"];
                return false;
            }


        }
        catch(e)
        {
            return false;
        }
 }

 if(values.indexOf("ismoney")>-1){ //金额 00.00
        try
        {

            if(!/^\d+\.\d{2}$/.test(val)){
               field.invalidText= sfieldname["ismoney"];
               return false;
            }

        }
        catch(e)
        {
            return false;
        }
 }



if(values.indexOf("max")>-1){ //超过最大值






      try
        {
            if((parseFloat(val) >= parseFloat(field.max))){
                 field.invalidText= sfieldname["max"];
                 return false;
            }
        }
        catch(e)
        {
            return false;
        }
 }

 if(values.indexOf("min")>-1){ //小于最小值






       try
        {
            if(!(parseFloat(val) >= parseFloat(field.min))){
                field.invalidText= sfieldname["min"];
                return false;
            }

        }
        catch(e)
        {
            return false;
        }
 }
 if(values.indexOf("minlength")>-1){ //最大长度






        try
        {
            if(!(val.length >= parseInt(field.minlen))){
               field.invalidText= sfieldname["minlength"];
               return false;
            }

        }
        catch(e)
        {
            return false;
        }
 }

 if(values.indexOf("maxlength")>-1){ //最小长度






     try
     {
        if(!(val.length <= parseInt(field.maxlen))){
          field.invalidText= sfieldname["maxlength"];
          return false;
        }
     }
     catch(e)
     {
        return false;
     }
 }
 if(values.indexOf("isusername")>-1){ //用户名



         try
        {
            if(!/^\w[\w-]*@\w[\w-]*$/.test(val)){
                field.invalidText= sfieldname["isusername"];
                return false;
            }


        }
        catch(e)
        {
            return false;
        }
 }
 if(values.indexOf("ismail")>-1){ //电子邮件
         try
        {
            if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(val)){
                field.invalidText= sfieldname["ismail"];
                return false;
            }


        }
        catch(e)
        {
            return false;
        }
 }
  if(values.indexOf("isCardNo ")>-1){ //电子邮件
         try
        {
            if(!/(^\d{15}$)|(^\d{17}(\d|X)$)/.test(val)){
                field.invalidText= sfieldname["isCardNo"];
                return false;
            }


        }
        catch(e)
        {
            return false;
        }
 }

    return true;
},


  password: function(val, field)
  {
        isblank(val,field);
        if (field.equalTo)
        {
		    //alert(field.equalTo)
            var pwd = Ext.get(field.equalTo);
            return (val == pwd.getValue());
        }
       // return true;
  },
  passwordText: '两次输入的密码不一致！',//两次输入的密码不一致！

  ischinese:function(val,field)
  {
        isblank(val,field);
        var reg = /^[\u4e00-\u9fa5]+$/i;
        if(!reg.test(val))
        {
            return false;
        }
        return true;
  },
  ischineseText:'请输入中文！',//请输入中文！
  
 iskeycode:function(val,field)
  {
        isblank(val,field);
        var reg = /^[a-zA-Z0-9][a-zA-Z0-9_-]*$/;
        if(!reg.test(val))
        {
            return false;
        }
        return true;
  },
  iskeycodeText:'只允许数字、字母、下划线、减号',//只允许数字、字母、下划线、减号
  
  isage:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(parseInt(val) >= 0 && parseInt(val) <= 150)
                return true;
            return false;
        }
        catch(err)
        {
            return false;
        }
  },
  ageText:'年龄输入有误！',//年龄输入有误！

  isalphanum:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(!/\W/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isalphanumText:'请输入英文字母或是数字,其它字符是不允许的.',//请输入英文字母或是数字,其它字符是不允许的.

  isurl:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^(http|https|ftp):\/\/(([A-Z0-9][A-Z0-9_-]*)(\.[A-Z0-9][A-Z0-9_-]*)+)(:(\d+))?\/?/i.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isurlText:'请输入有效的URL地址.',//请输入有效的URL地址.

  max:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(parseFloat(val) <= parseFloat(field.max))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  maxText:'超过最大值',//超过最大值

  min:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(parseFloat(val) >= parseFloat(field.min))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  minText:'小于最小值',//小于最小值

  isdatecn:function(val,field)
  {
        try
        {
            isblank(val,field);
            var regex = /^(\d{4})-(\d{2})-(\d{2})$/;
            if(!regex.test(val)) return false;
            var d = new Date(val.replace(regex, '$1/$2/$3'));
            return (parseInt(RegExp.$2, 10) == (1+d.getMonth())) && (parseInt(RegExp.$3, 10) == d.getDate())&&(parseInt(RegExp.$1, 10) == d.getFullYear());
        }
        catch(e)
        {
            return false;
        }
  },
  isdatecnText:'请使用这样的日期格式: yyyy-mm-dd. 例如:2008-06-20.',//请使用这样的日期格式: yyyy-mm-dd. 例如:2008-06-20.

  isinteger:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^[-+]?[\d]+$/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isintegerText:'请输入正确的整数',//请输入正确的整数

 isfloat:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^(\d*\.)?\d+$/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isfloatText:'请输入正确的浮点数',//请输入正确的浮点数

  minlength:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(val.length >= parseInt(field.minlen))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  minlengthText:'长度过小',//长度过小

  maxlength:function(val,field)
  {
     try
     {
        isblank(val,field);
        if(val.length <= parseInt(field.maxlen))
            return true;
        return false;
     }
     catch(e)
     {
        return false;
     }
  },
  maxlengthText:'长度过大',//长度过大

  isip:function(val,field)
  {
        try
        {
            isblank(val,field);
            if((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(val)))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isipText:'请输入正确的IP地址',//请输入正确的IP地址

  isphone:function(val,field)
  {
        try
        {
             isblank(val,field);
            if(/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isphoneText:'请输入正确的电话号码,如:0920-29392929',//请输入正确的电话号码,如:0920-29392929

  ismobilephone:function(val,field)
  {
        try
        {
             isblank(val,field);
            if(/(^0?[1][35][0-9]{9}$)/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  ismobilephoneText:'请输入正确的手机号码',//请输入正确的手机号码

  isalpha:function(val,field)
  {
        try
        {
            isblank(val,field);
            if( /^[a-zA-Z]+$/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  isalphaText:'请输入英文字母',//请输入英文字母

   iscode:function(val,field)
  {
        try
        {
            isblank(val,field);
            if( /^[0-9]{6}$/.test(val))
                return true;
            return false;
        }
        catch(e)
        {
            return false;
        }
  },
  iscodeText:'邮编格式不对！',//邮编格式不对！

  allowBlank:function(val,field)
  {
        try
        {
             isblank(val,field);
        }
        catch(e)
        {
            return false;
        }
  },
  allowBlankText:'内容不能为空',//内容不能为空

  ismoney:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^\d+\.\d{2}$/.test(val))
                return true;
        return false;
        }
        catch(e)
        {
            return false;
        }

  },
  ismoneyText:'请输入正确的金额',//请输入正确的金额

  isusername:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^\w[\w-]*@\w[\w-]*$/.test(val))
                return true;
        return false;
        }
        catch(e)
        {
            return false;
        }

  },
  //只允许数字/字母/下划线/减号;<br/>并且中间必须有一个@符号;<br/>例：user@group
  isusernameText:'只允许数字/字母/下划线/减号;<br/>并且中间必须有一个@符号;<br/>例：user@group',

  ismail:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(val))
                return true;
        return false;
        }
        catch(e)
        {
            return false;
        }

  },
  ismailText:'请输入正确的电子邮件格式！',//请输入正确的电子邮件格式！

  isCardNo:function(val,field)
  {
        try
        {
            isblank(val,field);
            if(/(^\d{15}$)|(^\d{17}(\d|X)$)/.test(val))
                return true;
        return false;
        }
        catch(e)
        {
            return false;
        }

  },
  isCardNoText:'请输入正确的身份证格式！'//请输入正确的身份证格式！

});
