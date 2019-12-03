<template>
	<view>
		<van-steps :active="active">
			<van-step>注册账号</van-step>
			<van-step>选择角色</van-step>
			<van-step>选择喜好</van-step>
			<van-step>注册完成</van-step>
		</van-steps>
		<van-button type="primary" size="large" @click="submit">继续</van-button>
		<!-- 根据拼音首字母建立关于所有tag的索引 用户可用点击其喜爱的tag将其记录 -->
		<van-index-bar>
			<van-index-anchor index="A" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'A'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="B" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'B'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="C" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'C'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="D" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'D'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="E" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'E'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="F" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'F'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="G" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'G'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="H" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'H'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="I" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'I'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="J" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'J'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="K" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'K'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="L" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'L'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="M" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'M'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="N" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'N'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="O" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'O'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="P" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'P'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Q" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Q'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="R" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'R'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="S" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'S'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="T" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'T'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="U" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'U'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="V" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'V'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="W" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'W'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="X" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'X'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Y" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Y'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>
			<van-index-anchor index="Z" />
				<van-cell v-for="(item,key) in tags.list" :key="item.id" v-if="item.pingin == 'Z'" clickable :title="item.name" :value="item.description" @click="clickTag(item.id, key)">
					<van-checkbox  :ref="'checkbox'+key" v-model="tags.like[key]" slot="right-icon" shape="square"></van-checkbox>
				</van-cell>	
		</van-index-bar>
		
	</view>
	
</template>

<script>
	export default {
		data() {
			return {
				active: 2,
				loading: true,
				userInfo: {},
				tags: {"like":[false],"list":[]},
				res:[]
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
			// console.log(this.userInfo);
			let pinyin = require('js-pinyin');
			pinyin.setOptions({checkPolyphone: false, charCase: 0});
			var rp = require('request-promise');
			// 获取所有tag
			rp('http://101.201.70.76:8211/getTags').then(res => {
			        // 获取所有Tag成功
					this.tags.list = JSON.parse(res);
					for (var i = 0; i < this.tags.list.length; i++) {
						var hp =  pinyin.getCamelChars(this.tags.list[i].name);
						this.tags.list[i].pingin = hp[0];	// 为了方便建立索引，给每个标签添加属性pingin以记录其name拼音首字母
						this.tags.like[i] = false;	// 为了方便判断用户选择的标签，以tag.like[i]标识第i个标签是否被选中
						// console.log(this.tags[i]);
					}
					 // console.log(this.tags);
					
			    }).catch(err => {
			        // 获取失败
					console.log(err)
			    });
		},
		methods: {
			// 每个标签点击时通过Checkbox实例上的 toggle 方法触发选择状态切换
			clickTag(id, key) {	
				this.$refs[`checkbox`+`${key}`][0].toggle();
				// console.log(this.$refs);
				// console.log(this.tags[key])
			},
			submit() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});
				
				var likecnt = 0;
				for(var i = 0; i < this.tags.list.length; i++) {
					if(this.tags.like[i]){
						this.res[likecnt++] = this.tags.list[i].id;
						// console.log(this.tags.list[i]);
					}
				}
				// for(var i = 0; i < this.res.length; i++) {
				// 	console.log(this.res[i]);
				// }
				var resStr = JSON.stringify(this.res)
				console.log(resStr);
				var rp = require('request-promise');
				var options = {
				    method: 'POST',
				    uri: 'http://101.201.70.76:8211/insertUserTag',
				    form: {
						uid: this.userInfo.id,
				        tags: resStr
				    }
				};
				rp(options).then(res => {
					this.$toast.clear();
					this.$toast.success('成功');
					this.$router.push({
						path: '/pages/register/registerCompleted', 
					});
					console.log(res);
				}).catch(err => {
					this.$toast.clear();
					this.$toast.fail('网络连接出错');
					console.log(err);
				});
				
			}
		}
		
	}
</script>

<style>
	
</style>
