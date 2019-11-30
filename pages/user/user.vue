<template>
	<view class=''>

		<view class='detail-container' :style ="'background:url(data:image/png;base64,'+userInfo.avatar + ') no-repeat  center/cover'"></view>
		<!--style='background: url(/testUserInfo/imgs/avatar.jpg) no-repeat  top/cover' -->
		<view class='detail-mask'></view>
		<view class='detail-info'>
			<img :src="'data:image/png;base64,'+userInfo.avatar" class='detail-img'/>
			<!-- <img :src="'data:image/png;base64,'+imgfile" class="avatar"/> -->
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
		<van-cell-group v-if="userInfo.power == 2">
			<van-cell title="审核调查" icon="todo-list-o" is-link />
			<van-cell title="管理公司" icon="friends-o" is-link />
			<van-cell title="管理用户" icon="user-o" is-link />
		</van-cell-group>
		
		<van-cell-group v-if="userInfo.power == 1">
			<van-cell title="发布调查" icon="todo-list-o" is-link @click="toCreateInv()" />
			<van-cell title="我的公司" icon="friends-o" is-link @click="toMyCmp()"/>
			<van-cell title="管理调查" icon="orders-o" is-link />
		</van-cell-group>
		
		<van-cell-group v-if="userInfo.power == 0">
			<van-cell title="我的调查" icon="description" is-link />
		</van-cell-group>
		
		<van-cell-group>
			<van-cell title="修改个人信息" icon="records" is-link />
		</van-cell-group>
		
		<van-cell-group v-if="userInfo.power != 2">
			<van-cell title="联系我们" icon="chat-o" is-link />
			<van-cell title="退出登录" icon="down" is-link />
		</van-cell-group>
		
		<van-tabbar route>
		  <van-tabbar-item icon="home-o" to="/home">首页</van-tabbar-item>
		  <van-tabbar-item icon="search" to="/home">检索</van-tabbar-item>
		  <van-tabbar-item icon="friends-o" to="/home">标签</van-tabbar-item>
		  <van-tabbar-item icon="manager" to="/home">我的</van-tabbar-item>
		</van-tabbar>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				userInfo: {avatar:""}
			}
		},
		onLoad() {
			var rp = require('request-promise');
			var options = {
			    method: 'POST',
			    uri: 'http://localhost:8080/getUserById',
			    form: {
			        id: this.$cookies.get("userInfo").id,
			    }
			};
			rp(options).then(res => {
				this.$toast.clear();
				if(res == "error") {
					this.$toast.fail('获取用户信息识别，未找到用户信息');
				} else {
					this.userInfo = JSON.parse(res);
					this.$toast.success('获取用户信息成功');
				}
				// console.log(this.note.backgroundImage)
			}).catch(err => {
				this.$toast.clear();
				this.$toast.fail('获取用户信息, 请检查网络连接');
				console.log(err);
			});
		},
		methods: {
			toCreateInv() {
				this.$router.push({
					path: '/pages/investigates/addInvestigates', 
				});
			},
			toMyCmp() {
				this.$router.push({
					path: '/pages/Company/userCompany', 
				});
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
