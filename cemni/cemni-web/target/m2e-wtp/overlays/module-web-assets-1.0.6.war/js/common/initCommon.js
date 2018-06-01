
/*
 * 固定不变的初始化参数
 * initStates action传给前台的列表过滤单据状态
 * initStatesStr 列表过滤单据状态字符串格式为枚举的完整类名：
 * com.datapower.scm.persistence.test.ProductState.New,
 * com.datapower.scm.persistence.test.ProductState.Confirm
 * checkboxgroup ,checkboxgroup 拼装查询复选框使用
 */ 
var initStatesStr = "";
var checkboxgroup = [];
var checkboxgroup1 = [];
var checkboxgroupSyn1 = [];
var checkboxgroupSyn2 = [];
//currentLang; //判断浏览器 语言环境  
var j = 0;
if(!Ext.isEmpty(initStates)){
	Q.each(initStates, function(o, i){
		initStatesStr += ","+o.stateCode;
		if(currentLang == 'en' || currentLang == 'en-US'){ 
			if(o.stateCode.length > 10 && checkboxgroup.length < 4){
				checkboxgroup.push({boxLabel: o.stateCode, name: 'initStates',checked: false,inputValue:o.stateCode});
		    }else{
		    	checkboxgroup1.push({boxLabel: o.stateCode, name: 'initStates',checked: false,inputValue:o.stateCode});
		    }; 
		}else{
			if(o.stateName.replace(/[ ]/g,"").length > 5){
				checkboxgroup.push({boxLabel: o.stateName, name: 'initStates',checked: false,inputValue:o.stateCode});
		    }else{
		    	checkboxgroup1.push({boxLabel: o.stateName, name: 'initStates',checked: false,inputValue:o.stateCode});
		    }; 
		} 
	    j++;
	});
}
if(initStates.length>0){
		initStatesStr = initStatesStr.substring(1);
}  
/*
 * 列表单据状态渲染时所用到的所有单据状态
 */
var billStateObj = {};
if(!Ext.isEmpty(billState)){
	Q.each(billState, function(o, i){
		if(currentLang == 'en' || currentLang == 'en-US'){ 
	    	billStateObj[o.stateCode]={id:o.stateId,code:o.stateCode,name:o.stateCode};
		}else{
			billStateObj[o.stateCode]={id:o.stateId,code:o.stateCode,name:o.stateName};
		}
	});
}

//同步状态  
var synStatuObj = {}; 
if(!Ext.isEmpty(synStatus)){
	Q.each(synStatus, function(o, i){
		if(currentLang == 'en' || currentLang == 'en-US'){ 
			synStatuObj[o.stateCode]={id:o.stateId,code:o.stateCode,name:o.stateCode}; 
			if(o.stateCode.length > 10 && checkboxgroup.length < 4){
				checkboxgroupSyn1.push({boxLabel: o.stateCode, name: 'synStatus',checked: false,inputValue:o.stateCode});
		    }else{
		    	checkboxgroupSyn2.push({boxLabel: o.stateCode, name: 'synStatus',checked: false,inputValue:o.stateCode});
		    }; 
		}else{
			synStatuObj[o.stateCode]={id:o.stateId,code:o.stateCode,name:o.stateName};
			
			if(o.stateName.replace(/[ ]/g,"").length > 5){
				checkboxgroupSyn1.push({boxLabel: o.stateName, name: 'synStatus',checked: false,inputValue:o.stateCode});
		    }else{
		    	checkboxgroupSyn2.push({boxLabel: o.stateName, name: 'synStatus',checked: false,inputValue:o.stateCode});
		    }; 
		}   
	});
} 

var playListMode ={normal:"normal",audit:"audit",undeal:"audit",panel:"panel"};//单据类型类型常量




