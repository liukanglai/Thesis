import{d as k,r as u,i as v,c as T,a,w as n,F as V,b as _,E as p,e as w,o as $,f as c,g as I,t as O}from"./index-2481af03.js";import{E as M}from"./overlay-dde63264.js";import{a as N,E as D}from"./table-column-140aeb9e.js";import{E as f}from"./index-dd477983.js";const q={class:"dialog-footer"},G=k({__name:"Fuzzy",setup(K){const l=u([]),h=u([{name:"操作时间"},{name:"操作精准度"},{name:"操作质量"}]),b=u([{name:"模具1"},{name:"模具2"},{name:"模具3"},{name:"模具4"}]),z=u([{name:"objectx"},{name:"objecty"},{name:"objectz"},{name:"anglex"},{name:"angley"},{name:"anglez"},{name:"speedx"},{name:"speedy"},{name:"speedz"}]),s=v(!1),o=u([]),i=localStorage.getItem("user"),g=()=>{s.value=!1},x=()=>{y().then(e=>{console.log("输入的值为："+e),_.get("/score/stepFuzzy/"+i+"/"+e).then(t=>{o.splice(0,o.length),Object.assign(o,t.data),l.splice(0,l.length,...h),s.value=!0}).catch(t=>{p.error("后台未连接！"),console.log(t)})}).catch(e=>{console.log(e.message)})},E=()=>{y().then(e=>{console.log("输入的值为："+e),_.get("/score/qualityFuzzy/"+i+"/"+e).then(t=>{o.splice(0,o.length),Object.assign(o,t.data),l.splice(0,l.length,...b),s.value=!0}).catch(t=>{p.error("后台未连接！"),console.log(t)})}).catch(e=>{console.log(e.message)})},C=()=>{F().then(e=>{console.log("输入的值为："+e),_.get("/score/objectFuzzy/"+i+"/"+e).then(t=>{o.splice(0,o.length),Object.assign(o,t.data),l.splice(0,l.length,...z),s.value=!0}).catch(t=>{p.error("后台未连接！"),console.log(t)})}).catch(e=>{console.log(e.message)})},y=()=>f.prompt("请输入0到7的数，表示各步骤","提示",{confirmButtonText:"OK",cancelButtonText:"Cancel",inputPattern:/^[0-7]$/,inputErrorMessage:"请输入0到7的整数",customClass:"custom-message-box"}).then(({value:e})=>e).catch(()=>{throw p({type:"info",message:"Input canceled"}),new Error("Input canceled")}),F=()=>f.prompt("请输入0到31的数，表示各步骤","提示",{confirmButtonText:"OK",cancelButtonText:"Cancel",inputPattern:/^[0-9]$|^1[0-9]$|^2[0-9]$|^3[0-1]$/,inputErrorMessage:"请输入0到31的整数",customClass:"custom-message-box"}).then(({value:e})=>e).catch(()=>{throw p({type:"info",message:"Input canceled"}),new Error("Input canceled")});return(e,t)=>{const r=w,m=N,B=D,j=M;return $(),T(V,null,[a(r,{type:"primary",onClick:x},{default:n(()=>[c("查看步骤操作详情模糊矩阵")]),_:1}),a(r,{type:"primary",onClick:E},{default:n(()=>[c("查看步骤模具模糊矩阵")]),_:1}),a(r,{type:"primary",onClick:C},{default:n(()=>[c("查看具体模具模糊矩阵")]),_:1}),a(j,{title:"模糊矩阵",modelValue:s.value,"onUpdate:modelValue":t[0]||(t[0]=d=>s.value=d),onClose:g},{footer:n(()=>[I("div",q,[a(r,{onClick:g},{default:n(()=>[c("取消")]),_:1}),a(r,{type:"primary",onClick:g},{default:n(()=>[c("确定")]),_:1})])]),default:n(()=>[a(B,{data:o},{default:n(()=>[a(m,{label:"Name"},{default:n(d=>[c(O(l[d.$index].name),1)]),_:1}),a(m,{prop:"0",label:"优秀"}),a(m,{prop:"1",label:"良好"}),a(m,{prop:"2",label:"一般"}),a(m,{prop:"3",label:"较差"})]),_:1},8,["data"])]),_:1},8,["modelValue"])],64)}}});export{G as _};