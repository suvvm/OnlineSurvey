<template>
	<view>
		<van-cell-group>
			<van-field
				v-model="username"
				label="用户名"
				left-icon="contact"
				placeholder="请输入用户名"
			/>
			<van-field
				v-model="name"
				label="姓名"
				left-icon="user-circle-o"
				placeholder="请输入真实姓名"
			/>
			<van-field
				v-model="email"
				label="邮箱"
				left-icon="free-postage"
				placeholder="请输入电子邮箱"
			/>
			<van-field
				v-model="pnum"
				label="手机号"
				left-icon="phone-o"
				placeholder="请输入手机号"
				:error-message="errpnum"
				@blur="onPnumChanged"
			/>		
			<van-field
				v-model="password"
				label="密码"
				type="password"
				left-icon="label-o"
				placeholder="请输入6-20位密码"
				maxlength="20"
					
			/>
			<van-field
				v-model="password1"
				type="password"
				label="确认密码"
				left-icon="label-o"
				placeholder="请再次输入密码"
				input="onPswChanged"
				maxlength="20"
				:error-message="err"		
				@blur="onPswChanged"		
			/>						
		</van-cell-group>
				
		<van-cell-group>
			<van-field
				v-model="sms"
				center
				clearable
				label="短信验证码"
				left-icon="chat-o"
				placeholder="请输入短信验证码">
				<van-button slot="button" size="small" :disabled="disabled" @click="sentIdentify" type="primary">
					<block v-if="count == 60">发送验证码</block>
					<block v-else>
						{{count}}
					</block>
					</van-button>
			</van-field>
		</van-cell-group>
		
		<van-cell-group>
			<label>上传头像</label>
			<van-uploader :after-read="afterRead" capture="camera"  multiple :max-count="1"/>
			<text v-if="imgfile != ''">选择完成</text>
		</van-cell-group>
		<van-cell-group>
			<label>人脸识别验证数据</label>
			<van-uploader :after-read="afterRead" capture="camera"  multiple :max-count="1"/>
			<text v-if="imgfile != ''">选择完成</text>
		</van-cell-group>
		<text v-if="imgfile != ''">选择完成</text>
		<van-checkbox v-model="checked">同意<van-button @click="showPopup" class="xieyi">《注册协议》</van-button></van-checkbox>
			<van-popup v-model="show" >《注册协议》</van-popup>
		<van-button class="submit" type="info" @click="handleClick">注册</van-button>				
						
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				disabled: false,
				checked: true,
				show: false,
				username: '',
				name:'',
				pnum:'',
				password: '',
				password1:'',
				sms:'',
				err:'',
				errpnum:'',
				identifyCode: '123',
				imgfile:'',
				time: 0,
				count: 60
			}
		},
		methods: {
			
		}
	}
</script>

<style>
	.point{
	    padding: 6% 5%;
	}
	.xieyi{
		background: #EFEFF4;
		font-size:18px; 
		font-style: italic;
	}
	.content form input:not(:nth-child(6)){
	    border: 0;
	    border-bottom: 1px solid #c3c3c5;
	}
	.content form{
	    width: 100%;
	    height: 35.21%;
	} 
	.message{
	    background: #ffffff;
	    padding:2% 5% 0 5%;
	    position: relative;
	}
	.message input{
	    width: 90%;
	    padding: 4% 0 4% 10%;
	    font-size: 0.875em;
	    font-family: "Microsoft YaHei";
	}
	.message .icons b{
	    position: absolute;
	    width: 3.75%;
	    height: 4%;
	    top: 7%;
	    left: 7%;
	}
	.message .icons b img{
	    width: 100%;
	}
	.message .icons b:nth-child(2){
	    width: 5%;
	    top: 26%;
	    left: 6%;
	}
	.message .icons b:nth-child(3){
	    top: 43%;
	}
	.message .icons b:nth-child(4){
	    top: 61%;
	}
	.code{
	    position: absolute;
	    top: 60%;
	    right:10%;
	    color: #21a9f5;
	    font-size: 0.875em;
	    font-family: "Microsoft YaHei";
	}
	select{
	    width: 33%;
	    padding: 2% 1%;
	    margin: 5%;
	    font-size: 1em;
	    color: #909093;
	    border: solid 1px #909093;
	    font-family: "Microsoft YaHei";
	}
	.agree{
	    margin: 6% 5%;
	}

	.submit{
	    width: 84%;
	    margin: 2% 8%;
	    background: #21a9f5;
	    color: #ffffff;
	    border: 0;
	    font-size: 1em;
	    font-family: "Microsoft YaHei";
	}
	form .agree input[type="checkbox"] :default{
	    outline: 2px solid #21a9f5;
	}
</style>
