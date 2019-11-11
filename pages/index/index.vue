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
		<van-cell-group class="username">
			<van-field
				v-model="password"
				type="password"
				label="密	码"
				placeholder="请输入密码"
				required
				:error-message="loginerr"
			/>
		</van-cell-group>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				title: 'index',
				username: '',
				password: '',
				pnum: '',
				loginerr:''
			}
		},
		onLoad() {

		},
		methods: {

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
		height: 200rpx;
		width: 200rpx;
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
	
	.username{
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
</style>
