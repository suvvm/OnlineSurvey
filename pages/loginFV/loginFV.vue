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
				username: '',
				imgfile: '',
				err: '',
			}
		},
		methods: {
			
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
