<template>
	<view>
		<van-pull-refresh v-model="isLoading" @refresh="onRefresh">
			<van-button icon="share" size="large" type="primary" @click="submit()">提交修改</van-button>
			<van-button icon="plus" size="large" type="info" @click="addTag()">添加标签</van-button>
			<van-panel v-for="(item,key) in tags.list" v-bind:key="key" :title="item.name" :desc="item.description" :status="tags.status[key]">
				<view slot="footer">
					<van-button size="small" @click="modifyTag(key)">修改</van-button>
					<van-button size="small" type="danger" @click="deleteTag(key)">删除</van-button>
					<van-button size="small" @click="recoverTag(key)" v-if="tags.add[key] || tags.mdf[key] || tags.del[key]">恢复</van-button>
				</view>
			</van-panel>
			
			<van-popup v-model="showMdfPopup" position="bottom">
				<van-cell-group>
					<van-field v-model="tempMdfName" label="标签名" left-icon="coupon-o"/>
					<van-field v-model="tempMdfDescription" label="描述" left-icon="comment-o"/>
					<van-button size="large" @click="confirmTag()"  type="info">确认</van-button>
				</van-cell-group>
			</van-popup>
			
			<van-popup v-model="showAddPopup" position="bottom">
				<van-cell-group>
					<van-field v-model="tempMdfName" label="标签名" left-icon="coupon-o"/>
					<van-field v-model="tempMdfDescription" label="描述" left-icon="comment-o"/>
					<van-button size="large" @click="confirmAddTag()"  type="info">确认</van-button>
				</van-cell-group>
			</van-popup>
		</van-pull-refresh>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				isLoading: false,
				tempMdfName: "",
				tempMdfDescription: "",
				tmepMdfKey: 0,
				showAddPopup: false,
				showMdfPopup: false,
				userInfo: {},
				tags: {"del":[],"list":[],"mdf":[],"status":[],"add":[]}
			}
		},
		onLoad() {
			
			this.$toast.loading({
				duration: 0,	// 持续展示 toast
				forbidClick: true,	// 禁用背景点击
				message: '加载中'
			});
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
			// console.log(this.userInfo);
			var rp = require('request-promise');
			// 获取所有tag
			rp('http://localhost:8080/getTags').then(res => {
				// 获取所有Tag成功
				
				this.tags.list = JSON.parse(res);
				for (var i = 0; i < this.tags.list.length; i++) {
					this.tags.del[i] = false;	// 为了方便判断用户删除的标签，以tag.del[i]标识第i个标签是否被删除，此操作也方便对删除操作的放弃\
					this.tags.mdf[i] = false;	// 为了方便判断用户修改的标签，以tag.del[i]标识第i个标签是否被修改，此操作也方便对修改操作的放弃
					this.tags.add[i] = false;
					this.tags.status[i] = "无状态";
				// 	console.log(this.tags[i]);
				// 	console.log(this.tags.list[i]);					
				}
				console.log(this.tags);
				this.$toast.clear();
				this.$toast.success('加载成功');
			}).catch(err => {
				// 获取失败
				this.$toast.clear();
				this.$toast.fail('加载失败，请检查网络连接');
				console.log(err)
			});
		},
		methods: {
			// 下拉刷新
			onRefresh() {
				setTimeout(() => {
					this.isLoading = false;
					if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
						this.userInfo = this.$cookies.get("userInfo");
					}
					// console.log(this.userInfo);
					var rp = require('request-promise');
					// 获取所有tag
					rp('http://localhost:8080/getTags').then(res => {
						// 获取所有Tag成功
						this.tags.list = JSON.parse(res);
						for (var i = 0; i < this.tags.list.length; i++) {
							this.tags.del[i] = false;	// 为了方便判断用户删除的标签，以tag.del[i]标识第i个标签是否被删除，此操作也方便对删除操作的放弃\
							this.tags.mdf[i] = false;	// 为了方便判断用户修改的标签，以tag.del[i]标识第i个标签是否被修改，此操作也方便对修改操作的放弃
							this.tags.add[i] = false;
							this.tags.status[i] = "无状态";
						// 	console.log(this.tags[i]);
						// 	console.log(this.tags.list[i]);					
						}
						console.log(this.tags);
						this.$toast.success('刷新成功');
					}).catch(err => {
						// 获取失败
						console.log(err)
					});
				}, 500);
			},
			addTag() {
				this.tempMdfName = "";
				this.tempMdfDescription = "";
				this.showAddPopup = true;
			},
			confirmAddTag() {
				var len = this.tags.list.length;
				var newTag = {"name":this.tempMdfName,"description":this.tempMdfDescription,"id":400000001+len}
				this.tags.list[len] = newTag;
				this.tags.status[len] = "新添加";
				this.tags.mdf[len] = false;
				this.tags.del[len] = false;
				this.tags.add[len] = true;
				this.showAddPopup = false;
				this.$forceUpdate();
			},
			modifyTag(key) {
				console.log(this.tags.list[key]);
				this.tmepMdfKey = key;
				this.showMdfPopup = true;
				this.tempMdfName = this.tags.list[key].name;
				this.tempMdfDescription = this.tags.list[key].description;
			},
			confirmTag(){
				var key = this.tmepMdfKey;
				this.tags.mdf[key] = true;
				this.tags.status[key] = "已修改"
				this.showMdfPopup = false;
				this.tags.list[key].name = this.tempMdfName;
				this.tags.list[key].description = this.tempMdfDescription;
				console.log(this.tags.list[key])
				this.$forceUpdate();
			},
			deleteTag(key) {
				this.tags.del[key] = true;
				this.tags.status[key] = "已删除"
				this.$forceUpdate();
			},
			recoverTag(key) {
				this.tags.del[key] = false;
				this.tags.mdf[key] = false;
				this.tags.status[key] = "已恢复";
				if(this.tags.add[key]) {
					this.tags.list.splice(key,1);
					this.tags.del.splice(key,1);
					this.tags.add.splice(key,1);
					this.tags.mdf.splice(key,1);
					this.tags.status.splice(key,1);
				}
				this.$forceUpdate();
			},
			submit() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});
				var add = [], del = [], mdf = [];
				var addCnt = 0, delCnt = 0, mdfCnt = 0;
				for(var i = 0; i < this.tags.list.length; i++){
					if(this.tags.add[i]) {
						var newTag = {"id":this.tags.list[i].id,"name":this.tags.list[i].name,"description":this.tags.list[i].description};
						add[addCnt++] = newTag;
					}else if(this.tags.del[i]) {
						var newDel = {"id":this.tags.list[i].id,"name":this.tags.list[i].name,"description":this.tags.list[i].description};
						del[delCnt++] = newDel;
					}else if(this.tags.mdf[i]) {
						var newTag = {"id":this.tags.list[i].id,"name":this.tags.list[i].name,"description":this.tags.list[i].description};
						mdf[mdfCnt++] = newTag;
					}
				}
				// console.log(JSON.stringify(add));
				// console.log(JSON.stringify(mdf));
				// console.log(JSON.stringify(del));
				
				var rp = require('request-promise');
				var options = {
					method: 'POST',
					uri: 'http://localhost:8080/updateTags',
					form: {
						add: JSON.stringify(add),
						mdf: JSON.stringify(mdf),
						del: JSON.stringify(del)
					}
				};
				rp(options).then(res => {
					this.$toast.clear();
					this.$toast.success('提交成功');
					onRefresh();
					console.log(res);
				}).catch(err => {
					this.$toast.clear();
					this.$toast.fail('提交失败，请检查网络连接');
					console.log(err);
				});
			}
		}
	}
</script>

<style>

</style>
