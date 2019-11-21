<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>联系作者</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		<van-notice-bar
		  text="本站暂时没有开启自助管理员申请,请手动发送邮件至作者邮箱suvvm@foxmail.com进行联系"
		  left-icon="volume-o"
		/>
		<view class="content">
			<image class="sad" src="../../static/sadPanda.png"></image>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				active: 2,
			}
		},
		onLoad() {
	
		},
		methods: {
			
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
