<template>
	<view class=''>

		<view class='detail-container' :style ="note"></view>
		<!--style='background: url(/testUserInfo/imgs/avatar.jpg) no-repeat  top/cover' -->
		<view class='detail-mask'></view>
		<view class='detail-info'>
			<img src="../../testUserInfo/imgs/avatar_suvvm.jpg" class='detail-img'></img>
			<view class='detail'>
				<view class='detail-nm'>{{userInfo.username}}</view>
				<view>姓名：{{userInfo.name}}</view>
				<view class='detail.sc'>电话：{{userInfo.pnum}}</view>
				<view>邮箱：{{userInfo.email}}</view>
				<view v-if="userInfo.gender == 0">性别：男</view> 
				<view v-else>性别：女</view> 
			</view>
		</view>
		<!-- 判断公司用户 管理员 普通用户后再做具体实现 -->
		<van-tabbar route>
		  <van-tabbar-item icon="home-o" to="/home">标签</van-tabbar-item>
		  <van-tabbar-item icon="search" to="/home">标签</van-tabbar-item>
		  <van-tabbar-item icon="friends-o" to="/home">标签</van-tabbar-item>
		  <van-tabbar-item icon="manager" to="/home">我的</van-tabbar-item>
		</van-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				text: "suvvm",
				userInfo: {},
				note: {
				    backgroundImage: "url(" + require("../../testUserInfo/imgs/avatar_suvvm.jpg") + ")",
				    backgroundRepeat: "no-repeat",
				    backgroundPosition: "center",
				}
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){
				this.userInfo = this.$cookies.get("userInfo");
			}
		},
		methods: {
			submit() {
				
			}
		}
	}
</script>

<style>
	.test{
		height: 400rpx;
		width: 400rpx;
	}
	.detail-container {
		height: 400rpx;
		filter: blur(40rpx);
		opacity: 0.4;
	}
	  
	.detail-mask {
		position: absolute;
		width: 100%;
		height: 400rpx;
		background-color: #333;
		top: 0;
		left: 0;
		z-index: -1;
	}
	  
	.detail-img {
		width: 280rpx;
		height: 70%;
		margin-right: 24rpx;
	}
	  
	.detail-info {
		display: flex;
		position: absolute;
		top: 0;
		left: 0;
		width: 100%;
		height: 400rpx;
		padding: 20rpx;
	}
	  
	.detail {
		flex-grow: 1;
		line-height: 60rpx;
	}
	  
	.detail view {
		color: #fff;
		font-size: 24rpx;
	}
	  
	.detail .detail-nm {
		font-size: 40rpx;
		font-weight: 700;
	}
	  
	.detail .sc {
		color: #fc0;
		font-size: 36rpx;
		font-weight: 700;
	}
	  
	.desc {
		padding: 20rpx;
		color: #555;
		font-size: 24rpx;
	}
	  
	.comment-container {
		padding: 0 20rpx;
	}
	  
	.comment {
		padding: 10rpx;
	}
	  
	.comment-content {
		border: 1px solid #ccc;
		width: 100%;
		box-sizing: border-box;
		font-size: 32rpx;
		border-radius: 8rpx;
		padding: 20rpx;
	}
	  
	.comment-image image {
		width: 200rpx;
		height: 200rpx;
		margin: 10rpx;
	}
	  
	.comment-img {
		width: 200rpx;
		height: 200rpx;
		margin: 20rpx 20rpx 0 0;
	}
</style>
