<template>
	<view>
		<van-button icon="share" size="large" type="primary" @click="submit()">提交审核</van-button>
		<van-button icon="plus" size="large" type="info" @click="addTag()">新增问题</van-button>
		<van-cell-group>
			<van-field v-model="name" label="问卷名" placeholder="请输入问卷名" required />
			<van-field v-model="description" rows="1" autosize label="问卷简介" type="textarea" placeholder="请输入简介"/>
		</van-cell-group>
		<van-collapse v-model="activeNames">
		  <van-collapse-item title="选择标签" name="1">
			  <van-checkbox-group v-model="result" :max="3">
			    <van-cell-group>
			      <van-cell v-for="(item, key) in tags" clickable v-bind:key="key" :title="item.name" @click="toggle(key)">
			        <van-checkbox :name="item" ref="checkboxes" slot="right-icon"/>
			      </van-cell>
			    </van-cell-group>
			  </van-checkbox-group>
		  </van-collapse-item>
		 
		</van-collapse>
		
		
		<view v-for="(item,key) in questionList" v-bind:key="key">
			<van-panel v-if="item.type == 0"  :title="item.stem" status="选择题">
				<view v-for="(opt,num) in item.options" v-bind:key="num">
					<van-field :value="opt.value" :label="opt.option" disabled/>
				</view>
				<view slot="footer">
					<van-button size="small" @click="modifyQst(key, 0)">修改</van-button>
					<van-button size="small" type="danger" @click="deleteQst(key)">删除</van-button>
				</view>
			</van-panel>
			<van-panel v-else-if="item.type == 1"  :title="item.stem" status="简答题">
				<view slot="footer">
					<van-button size="small" @click="modifyQst(key, 1)">修改</van-button>
					<van-button size="small" type="danger" @click="deleteQst(key)">删除</van-button>
				</view>
			</van-panel>
		</view>
		
		
		
		<van-popup v-model="showAddQst" position="bottom">
			<van-tabs v-model="active" animated>
				<van-tab title="选择题">
					<van-cell-group>
					  <van-field v-model="addQstStem" rows="1" autosize label="题干" type="textarea" placeholder="请输入题干"/>
					</van-cell-group>
					<van-cell-group v-for="(item, key) in addQstOptions" v-bind:key="key">
					  <van-cell :title="item.option" value="选项"  :label="item.value" />
					</van-cell-group>
					<van-button type="primary" size="large" @click="createOption()">新增选项</van-button>
					<van-button type="info" size="large" @click="confirmQst(0)">提交</van-button>
				</van-tab>
				<van-tab title="简答题">
					<van-cell-group>
					  <van-field v-model="addQstStem" rows="1" autosize label="题干" type="textarea" placeholder="请输入题干"/>
					</van-cell-group>
					<van-button type="info" size="large" @click="confirmQst(1)">提交</van-button>
				</van-tab>
			</van-tabs>
		</van-popup>
		
		<van-popup v-model="showAddOpt" position="top">
			<van-cell-group>
			  <van-field v-model="newOptionVal" rows="1" label="选项内容" placeholder="选项内容"/>
			  <van-button type="primary" size="large" @click="confirmOption()">确定</van-button>
			</van-cell-group>
		</van-popup>
		
		<van-popup v-model="showMdfQst" position="top">
			<van-panel v-if="questionList[mdfKey].type == 0"  title="选择题修改">
				<van-field v-model="questionList[mdfKey].stem"/>
				<view v-for="(opt,num) in questionList[mdfKey].options" v-bind:key="num">
					<van-field v-model="questionList[mdfKey].options[num].value" :label="opt.option"/>
				</view>
				<view slot="footer">
					<van-button size="small" type="info" @click="confirmModify()">修改</van-button>
				</view>
			</van-panel>
			<van-panel v-else-if="questionList[mdfKey].type == 1"  title="简答题修改">
				<van-field v-model="questionList[mdfKey].stem"/>
				<view slot="footer">
					<van-button size="small" @click="confirmModify()">修改</van-button>
				</view>
			</van-panel>
		</van-popup>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: "",
				questionList: [{stem:"徐英豪是不是沙雕",options:[{option:"A",value:"是"}],type:3}],
				userInfo: {},
				showAddQst: false,
				showAddOpt: false,
				showMdfQst: false,
				active: 1,
				addQstStem: "",
				newOptionVal: "",
				addQstOptions: [],
				mdfKey: 0,
				tags: [],
				activeNames: [],
				result: [],
				description: ""
			}
		},
		onLoad() {
			if(this.$cookies.get("userInfo") != null){	// 获取当前用户信息
				this.userInfo = this.$cookies.get("userInfo");
			}
			var rp = require('request-promise');
			// 获取所有tag
			rp('http://101.201.70.76:8211/getTags').then(res => {
				// 获取所有Tag成功
				
				this.tags = JSON.parse(res);
				// console.log(this.tags);
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
			addTag() {
				this.showAddQst = true;
			},
			createOption() {
				this.showAddOpt = true;
				this.newOptionVal = "";
			},
			confirmOption() {
				var newOpt = {"option": String.fromCharCode(65+this.addQstOptions.length),"value":this.newOptionVal}
				this.addQstOptions[this.addQstOptions.length] = newOpt;
				this.newOptionVal = "";
				this.$forceUpdate();
			},
			toggle(index) {
			    this.$refs.checkboxes[index].toggle();
				console.log(this.result);
			},
			confirmQst(val) {
				if(val == 0) {
					if(this.addQstOptions.length == 0) {
						this.$toast.fail("选项不能为空!");
					} else if(this.addQstStem == "") {
						this.$toast.fail("题干不能为空!");
					} else {
						var newQst = {"stem":this.addQstStem, "options": this.addQstOptions, "type":0};
						// console.log(newQst);
						this.questionList[this.questionList.length] = newQst;
						this.addQstStem = "";
						this.addQstOptions = [];
						this.$forceUpdate();
					}
				} else if(val == 1) {
					if(this.addQstStem == "") {
						this.$toast.fail("题干不能为空!");
					} else {
						var newQst = {"stem":this.addQstStem, "options":null, "type":1}
						console.log(newQst);
						this.questionList[this.questionList.length] = newQst;
						this.addQstStem = "";
						this.$forceUpdate();
					}
				}
			},
			deleteQst(key) {
				this.questionList.splice(key,1);
				this.$forceUpdate();
			},
			modifyQst(key, type) {
				this.mdfKey = key;
				this.showMdfQst = true;
			},
			confirmModify(key, type) {
				this.$forceUpdate();
			},
			submit() {
				this.$toast.loading({
					duration: 0,	// 持续展示 toast
					forbidClick: true,	// 禁用背景点击
					message: '提交中'
				});
				
				this.questionList.splice(0,1);
				var rp = require('request-promise');	
				var options = {
					method: 'POST',
					uri: 'http://localhost:8080/insertInvestigate',
					form: {
						description: this.description,
						name: this.name,
						ownerId: this.userInfo.id,
						invDetails: JSON.stringify(this.questionList),
						tags: JSON.stringify(this.result)
					}
				};
				rp(options).then(res => {
					this.$toast.clear();
					this.$toast.success('提交成功，请耐心等待审核');
					
					// console.log(res);
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
