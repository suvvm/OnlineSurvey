<template>
	
	<view class=''>
		<view class='detail-container' style='background: url(/testUserInfo/imgs/face.jpg) no-repeat  top/cover'></view>
		<view class='detail-mask'></view>
		<view class='detail-info'>
			<img src="../../testUserInfo/imgs/avatar.jpg" class='detail-img'></img>
			<view class='detail'>
				<view class='detail-nm'>123</view>
				<view>123</view>
				<view class='detail.sc'>0分</view>
				<view>123</view>
				<view>导演：sb</view> 
			</view>
			</view>
			<view class='desc'>qwe</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			testSubmit() {
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://localhost:8080/test',
				    form: {
				        // Like <input type="text" name="name">
				        text1: 'showText1',
						text2: 'showText2'
				    },
				    headers: {
				        /* 'content-type': 'application/x-www-form-urlencoded' */ // Is set automatically
				    }
				};
				rp(options)
				    .then(res => {
				        // POST succeeded...
						console.log(res)
				    })
				    .catch(err => {
				        // POST failed...
						console.log(err)
				    });
			}
		}
	}
</script>

<style>
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
