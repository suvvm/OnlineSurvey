<template>
	<view>
		<van-notice-bar
		  text="有疑问可至本人博客页联系,或手动发送邮件至作者邮箱suvvm@foxmail.com"
		  left-icon="volume-o"
		/>
		<view class="content">
			<image class="sad" src="../../static/sadPanda.png"></image>
			<van-button type="info" size="large" @click="toSuvvmBlog()">作者博客</van-button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			toSuvvmBlog() {
				window.location.href = 'https://www.suvvm.work/'; 
			}
		}
	}
</script>

<style>
	.content {
		display: flex;
		flex-direction: column;
		align-items: center;
	}
	.sad {
		height: 600rpx;
		width: 600rpx;
		margin-top: 100rpx;
		margin-left: auto;
		margin-right: auto;
		margin-bottom: 50rpx;
	}
</style>
