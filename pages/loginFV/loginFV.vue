<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<van-cell-group class="username">
		  <van-field
			v-model="pnum"
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
		<van-uploader :after-read="afterRead">
		  <van-button class="submit" icon="photo"  type="primary" size="large" >人脸识别登录</van-button>
		</van-uploader>
		<van-button class="submit"  plain hairline size="large"  @click="toRegister"  type="primary">立刻注册</van-button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				pnum: '',
				imgfile: '',
				err: '',
			}
		},
		methods: {
			afterRead(file) {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '识别中'
				});
				// 压缩图片
				lrz(file.file, {
					quality: 0.7 
				}).then(rst=> {
					// 处理成功会执行
					var rp = require('request-promise');
					var options = {
						method: 'POST',
						uri: 'http://localhost:8080/fVerify',
						form: { 
							pnum: this.pnum,
							content_1: rst.base64.slice(23)
						}
					};
					// post发送对应人脸识别请求
					rp(options).then(res => {
						this.$toast.clear();
					    // POST 成功
						console.log(res);
						if (res != 'error') {
							var faceInfo = JSON.parse(res);
							// 提取用户信息
							var userInfo = {"username":faceInfo.username,"name":faceInfo.name,"pnum":faceInfo.pnum,
							"email":faceInfo.email,"gender":faceInfo.gender,"power":faceInfo.power,"id":faceInfo.id};
							if(faceInfo.confidence >= 80) {	// 相似度大于80%判定为同一人
								this.$cookies.set("userInfo", userInfo, 60 * 60  * 24 * 7);
								this.$toast.success('登录成功');
								this.$router.push({
									path: '/pages/user/user', 
								});
							} else {
								this.$toast.fail('非本人');
							}
						} else {
							this.$toast.fail('识别失败，请检查账号信息');
						}	
					}).catch(err => {
					    // POST 失败
						this.$toast.clear();
						console.log(err);
						this.$toast.fail('识别失败，请检查网络连接');
					});
					
				}).catch(err => {
					// 处理失败会执行
					this.$toast.fail("图片获取失败！")
				})
				this.imgfile = file.content.slice(23);
				// console.log(file.content.slice(23));
			},
			
		}
	}
</script>

<style>
	.logo {
			height: 500rpx;
			width: 400rpx;
			margin-top: 200rpx;
			margin-left: auto;
			margin-right: auto;
			margin-bottom: 50rpx;
		}
	.content{
		text-align: center;
		height: 400upx;
		background: #ffffff;
	}
	.username{
		width: 80%;
		margin-left: 10%;
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
	}
	.submit{
		margin-top: 10rpx;
		width: 600rpx;
	}
</style>
