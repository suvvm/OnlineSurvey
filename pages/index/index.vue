<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<van-cell-group class="uinfoGroup">
			<van-field
				v-model="username"
				required
				clearable
				label="用户名"
				right-icon="question-o"
				placeholder="请输入用户名"
				@click-right-icon="$toast('可输入用户名手机号或邮箱')"
				rows="1"
				autosize
			/>
		</van-cell-group> 
		<van-cell-group class="uinfoGroup">
			<van-field
				v-model="password"
				type="password"
				label="密	码"
				placeholder="请输入密码"
				required
			/>
		</van-cell-group>
		<van-cell-group class="btnGroup">
			<van-button plain class="submit" type="primary"  size="large" @click="toRegister" hairline >立刻注册</van-button>
			<van-button class="submit" type="primary"  size="large" @click="handleClick">确定</van-button>
			<van-button plain class="submit" type="primary"  size="large" @click="toFVlogin" hairline>人脸识别</van-button>
		</van-cell-group>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				username: '',
				password: '',
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){	// 登录状态判断
				this.$router.push({
					path: '/pages/user/user'
				})
			}
		},
		methods: {
			toFVlogin() {
				this.$router.push({
					path: '/pages/loginFV/loginFV', 
				})
			},
			toRegister() {
				this.$router.push({
					path: '/pages/register/register', 
				})
			},
			handleClick() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '登录中'
				});
				
				
				
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/login',
				    form: {
				        username: this.username,
						password: this.password
				    }
				};
				rp(options).then(res => {
					this.$toast.clear();
					if(res == null) {
						this.$toast.fail('用户名或密码错误');
					} else {
						var userInfo = JSON.parse(res);
						this.$cookies.set("userInfo", userInfo, 60 * 60  * 24 * 7);
						this.$toast.success('登录成功');
						this.$router.push({
							path: '/pages/user/user', 
						})
					}
					console.log(res)
				}).catch(err => {
					this.$toast.clear();
					this.$toast.fail('网络连接出错');
					console.log(err)
				});
			}
		}
	}
</script>

<style>
	@import 'vant/lib/index.css';

	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
		justify-content: center;
	}

	.logo {
		height: 500rpx;
		width: 400rpx;
		margin-top: 200rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}

	.text-area {
		display: flex;
		justify-content: center;
	}

	.title {
		font-size: 36rpx;
		color: #8f8f94;
	}
	
	.uinfoGroup{
		width: 80%;
		display: block;
		padding:0 0 ;
		background: #ffffff;
		border: 0;
		font-size: 1em;
		font-family: "Microsoft YaHei";
		-webkit-box-shadow: #8f9bad 0px 0px 20px;
		-moz-box-shadow: #8f9bad 0px 0px 20px;
		box-shadow: #8f9bad 0px 0px 20px;
		text-align: center;
		height: 80rpx;
	}
	.submit{
		width: 27%;
		height: 80rpx;
		display: inline-block;
	}
	.btnGroup{
		width: 100%;
		text-align: center;
		margin-top: 30rpx;
	}
</style>
