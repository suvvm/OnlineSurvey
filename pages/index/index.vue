<template>
	<view class="content">
		<image class="logo" src="/static/logo.png"></image>
		<view class="text-area">
			<van-button type="default">默认按钮</van-button>
			<van-button type="primary">主要按钮</van-button>
			<van-button type="info">信息按钮</van-button>
			<van-button type="warning">警告按钮</van-button>
			
		</view>
		<view><van-checkbox v-model="checked">复选框</van-checkbox></view>
	</view>
</template>

<script>
	
	export default {
		data() {
			return {
				title: 'Hello',
				username: 'Hello',
				password: 'Hello'
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
</style>
