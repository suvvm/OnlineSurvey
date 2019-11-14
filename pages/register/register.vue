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
			<van-uploader :after-read="afterReadAvatar" capture="camera"  multiple :max-count="1"/>
			<text v-if="avatar != ''">选择完成</text>
		</van-cell-group>
		<van-cell-group>
			<label>人脸识别验证数据</label>
			<van-uploader :after-read="afterReadFace" capture="camera"  multiple :max-count="1"/>
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
				email: '',
				name:'',
				pnum:'',
				gender: '',
				password: '',
				password1:'',
				sms:'',
				err:'',
				errpnum:'',
				identifyCode: '123',
				imgfile: '',
				avatar: '',
				time: 0,
				count: 60
			}
		},
		methods: {
			onPswChanged() {
				if(this.password != this.password1) {
					this.err = "两次输入不一致"
				} else {
					this.err = ""
				}
			},
			onPnumChanged(){
				if(this.pnum.length != 11){
					this.errpnum = "手机号格式错误"
				}else{
					this.errpnum = ""
				}
			},
			afterReadAvatar(file) {
				// 压缩图片
				lrz(file.file, {
					quality: 0.7 
				}).then(rst=> {
					// 处理成功会执行
					this.avatar = rst.base64.slice(23);	 
				}).catch(err => {
					// 处理失败会执行
					this.$toast.fail("上传失败")
				})
				this.avatar = file.content.slice(23);
				console.log(file.content.slice(23));
			},
			afterReadFace(file) {
				// 压缩图片
				lrz(file.file, {
					quality: 0.7 
				}).then(rst=> {
					// 处理成功会执行
					this.imgfile = rst.base64.slice(23);	 
				}).catch(err => {
					// 处理失败会执行
					this.$toast.fail("上传失败")
				})
				this.imgfile = file.content.slice(23);
				console.log(file.content.slice(23));
			},
			handleClick() {
				var rp = require('request-promise');
				var options = {
					method: 'POST',
					uri: '',
					form: {
						// Like <input type="text" name="name">
						username:this.username,
						password: this.password,
						name: this.name,
						pnum: this.pnum,
						email: this.email,
						gender: this.gender,
						avatar: this.avatar,
						imgbase64: this.imgfile,
						power: '0'
					},
				    headers: {
				        /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
				    }
				};
				rp(options).then(res => {
				        // POST succeeded...
					console.log(res)
				}).catch(err => {
				        // POST failed...
					console.log(err)
				});
			}
		}
	}
</script>

<style>

	.xieyi{
		background: #EFEFF4;
		font-size:18px; 
		font-style: italic;
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
</style>
