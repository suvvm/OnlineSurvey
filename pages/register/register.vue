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
			<van-radio-group v-model="gender">
			  <van-cell-group>
			    <van-cell title="我是男性" clickable @click="gender = '0'">
			      <van-radio slot="right-icon" name="0" />
			    </van-cell>
			    <van-cell title="我是女性" clickable @click="gender = '1'">
			      <van-radio slot="right-icon" name="1" />
			    </van-cell>
			  </van-cell-group>
			</van-radio-group>
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
			<img :src="'data:image/png;base64,'+avatar" class='innerImg2'/>
		</van-cell-group>
		<van-cell-group>
			<label>上传人脸</label>
			<van-uploader :after-read="afterReadFace" capture="camera"  multiple :max-count="1"/>
			<img :src="'data:image/png;base64,'+imgfile" class='innerImg2'/>
		</van-cell-group>
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
				gender: '0',
				password: '',
				password1:'',
				sms:'',
				err:'',
				errpnum:'',
				identifyCode: '',
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
				// console.log(file.content.slice(23));
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
				// console.log(file.content.slice(23));
			},
			sentIdentify() {
				// 发送验证码按钮倒计时
				const TIME_COUNT = 60;
				if (!this.time) {
					this.count = TIME_COUNT;
					this.disabled = true;
					this.time = setInterval(() => {
						if(this.count > 0 && this.count <= TIME_COUNT) {
							this.count--;
						} else {
							this.disabled = false;
							clearInterval(this.time);
							this.time = 0;
							this.count = TIME_COUNT
						}
					}, 1000)
				}
				
				// 生成4位随机手机验证码
				var i = Math.random()*(999999-100000)+100000; 
				var j = parseInt(i,10); 
				this.identifyCode = j;
				
				var rp = require('request-promise');
				// 以表单形式向后端SmsController发送post请求
				var options = {
					method: 'POST',
					uri: 'http://101.201.70.76:8211/getSms',
					form: {
						// 发送手机号和验证码格式化的json串
						mobile: this.pnum,
						code: "{\"code\":\""+this.identifyCode+"\"}"
					},
				};
				rp(options).then(res => {
				    // POST 成功
					if (res != 'error') {
						this.$toast.success('发送成功');
					} else {
						this.$toast.fail('发送失败，请检查网络连接');
					}	
				}).catch(err => {
				    // POST 失败
					// console.log(err);
					this.$toast.fail('发送失败，请检查网络连接');
				});
			},
			handleClick() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '注册中'
				});
				
				if(this.sms == '' || this.username == '' || this.password == '' || this.name == '' ||
					this.pnum == '' || this.email == '' || this.avatar == '' || this.imgfile == '') {
						this.$toast.clear();
						this.$toast.fail('请填写必填信息');
						return;
					}
				
				
				if(this.sms != this.identifyCode){
					this.$toast.clear();
					this.$toast.fail('验证码错误，请准确填写验证码！');
					return;
				}
					
				// console.log("username:" + this.username + "password:" 
				// + this.password + " name:" + this.name + " pnum:" + this.pnum 
				// + " email:" + this.email + " gender:" + this.gender);
				var rp = require('request-promise');
				var options = {
					method: 'POST',
					uri: 'http://101.201.70.76:8211/insertUser',
					form: {
						// 模拟表单的形式向UserController发送insertUser请求
						username: this.username,
						password: this.password,
						name: this.name,
						pnum: this.pnum,
						email: this.email,
						gender: this.gender,
						avatar: this.avatar,
						imgbase64: this.imgfile,
						power: '0'
					}
				};
				rp(options).then(res => {
					// POST succeeded...
					this.$toast.clear();
					this.$toast.fail('注册成功！');
					// 以对应用户信息构建JSONObject
					
					var user = JSON.parse(res);
					// 提取用户信息
					var userInfo = {"username":user.username,"name":user.name,"pnum":user.pnum,
					"email":user.email,"gender":user.gender,"power":user.power,"id":user.id};
					
					// console.log(userInfo);
					
					// 存cookies
					this.$cookies.set("userInfo", userInfo, 60 * 60  * 24 * 7);
					// 跳转到角色选择页
					this.$router.push({
						path: '/pages/register/registerRole', 
					})
					// console.log(res);
				}).catch(err => {
					this.$toast.clear();
					this.$toast.fail('注册失败，请检查网络连接！');
						// POST failed...
					// console.log(err);
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
	.innerImg{
		width: 200rpx;
		height: 200rpx;
	}
	.innerImg2{
		width: 200rpx;
		height: 250rpx;
	}
</style>
