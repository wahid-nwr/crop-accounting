!function(widget,$){"use strict";var SHOW_ALL="#SHOW_ALL",A_SELECTED="aria-selected",A_CONTROLS="aria-controls",A_HIDDEN="aria-hidden",HINT_CLASS="apex-rds-item--hint",AFTER_CLASS="apex-rds-after",BEFORE_CLASS="apex-rds-before",SELECTED_CLASS="apex-rds-selected",MIN_TAB_WIDTH=100,FILL_CLASS="apex-rds--fill",RTL_CLASS="u-RTL",keys=$.ui.keyCode;widget.regionDisplaySelector=function(pRegionDisplaySelectorRegion,pOptions){var tabsContainer$,moveNext,movePrevious,tabs={},isAnimating=!1,activeTab=null,firstVisibleTab=null,rtlMode=!1,sessionStorage=apex.storage.getScopedSessionStorage({usePageId:!0,useAppId:!0,regionId:pRegionDisplaySelectorRegion});pOptions=jQuery.extend({},{mode:"standard",showHints:!1,useSlider:!0,useLocationHash:!1,useSessionStorage:!0,addMoveNextPrevious:!1,hidePreviousTab:!0,onRegionChange:function(mode,activeTab){var regionChangeListener=tabsContainer$.data("onRegionChange");regionChangeListener&&regionChangeListener(mode,activeTab)}},pOptions);var pJumpMode="jump"===pOptions.mode,pUseSlider=pOptions.useSlider,pUseLocationHash=pOptions.useLocationHash,pUseSessionStorage=pOptions.useSessionStorage,onRegionChange=pOptions.onRegionChange,iterateThroughAndClear=function(tab,pMoveToPrevious,pShowAll){for(;null!=tab;)tab.link$.parent().removeClass(SELECTED_CLASS),tab.el$.removeClass("apex-rds-element-selected"),pJumpMode?tab.el$.attr(A_HIDDEN,"false"):pShowAll?tab.hidden||(tab.el$.attr(A_HIDDEN,"false"),tab.el$.show()):(pOptions.hidePreviousTab&&tab.el$.hide(),tab.el$.attr(A_HIDDEN,"true")),tab.parent$.removeClass(HINT_CLASS).attr(A_SELECTED,!1),tab.link$.attr("tabIndex","-1"),pMoveToPrevious?(tab.el$.removeClass(AFTER_CLASS).addClass(BEFORE_CLASS),tab.link$.parent().removeClass(AFTER_CLASS).addClass(BEFORE_CLASS),tab=tab.previous):(tab.el$.addClass(AFTER_CLASS).removeClass(BEFORE_CLASS),tab.link$.parent().addClass(AFTER_CLASS).removeClass(BEFORE_CLASS),tab=tab.next)},inFillMode=function(){return tabsContainer$.hasClass(FILL_CLASS)},fillMode=null,userWantsFillMode=function(){return null==fillMode&&(fillMode=inFillMode()),fillMode},displayIsTable=function(){return"table"===tabsContainer$.css("display")},slider$=null,initializeSlider=function(){if(!inFillMode()&&!displayIsTable()&&pUseSlider&&null==slider$){var leftHoverNode$=$('<div class="apex-rds-hover left"><a> <span class="a-Icon icon-left-chevron"></span> </a></div>'),rightHoverNode$=$('<div class="apex-rds-hover right" ><a> <span class="a-Icon icon-right-chevron"></span> </a></div>');slider$=$("<div class='apex-rds-slider'>"),slider$.append(leftHoverNode$).append(rightHoverNode$),tabsContainer$.parent().prepend(slider$);var scrollDebouncer,hoverNode=function(hover$,right){var loop=function(){var offset=right?"+=20px":"-=20px";tabsContainer$.stop().animate({scrollLeft:offset},100,"linear",loop),checkState()},stop=function(){tabsContainer$.stop()};hover$.click(function(){var offset=right?"+=200px":"-=200px";tabsContainer$.stop(!1,!1).animate({scrollLeft:offset},100)});var checkState=function(){var hasHitBounds,padding=parseInt(tabsContainer$.css("paddingRight"))+parseInt(tabsContainer$.css("paddingLeft")),scrollWidth=tabsContainer$[0].scrollWidth-padding,width=tabsContainer$.width(),maxScrollLeft=scrollWidth-width,minScrollLeft=0,scrollLeft=tabsContainer$.scrollLeft();return(hasHitBounds=right?scrollLeft>=maxScrollLeft:scrollLeft===minScrollLeft)?(hover$.hide(),!1):(hasHitBounds||hover$.show(),!0)};return hover$.hover(loop,stop),{checkState:checkState}},hoverRight=hoverNode(rightHoverNode$,!0),hoverLeft=hoverNode(leftHoverNode$,!1),checkState=function(){hoverLeft.checkState(),hoverRight.checkState()};tabsContainer$.scroll(function(){clearTimeout(scrollDebouncer),scrollDebouncer=setTimeout(checkState,200)}),$(window).on("apexwindowresized",checkState),checkState()}},buildTabs=function(){var realNumberOfTabs=0;tabsContainer$=$("#"+pRegionDisplaySelectorRegion+"_RDS"),tabsContainer$.attr("role","tablist"),"rtl"===tabsContainer$.css("direction")&&(tabsContainer$.addClass(RTL_CLASS),rtlMode=!0);var onClick=function(currentTab,e){currentTab.makeActive(!0),void 0!=e&&e.doNotFocus||currentTab.link$.focus()};movePrevious=function(tab,options){moveToRegion(tab,"getPreviousVisible","getNextVisible",options)},moveNext=function(tab,options){moveToRegion(tab,"getNextVisible","getPreviousVisible",options)};var moveToRegion=function(tab,key,backwardKey,event){var forward=tab[key]();if(null!=forward)onClick(forward,event);else{for(var tab1=tab,backward=tab1[backwardKey]();null!=backward;)tab1=backward,backward=backward[backwardKey]();onClick(tab1,event),firstVisibleTab.el$.addClass("apex-rds-swap"),lastVisibleTab.el$.addClass("apex-rds-swap")}},tabShouldBeHidden=function(){return!pJumpMode&&pOptions.hidePreviousTab},getFirstUnhiddenTab=function(tab,direction){if(!tab)return null;if(!tab.hidden)return tab;for(;tab[direction];)if(tab=tab[direction],!tab.hidden)return tab;return null},showTabInList=function(tab){tab.hidden=!1;var nextVisible=tab.getNextVisible();null===nextVisible&&(lastVisibleTab=tab);var previousVisible=tab.getPreviousVisible();null===previousVisible&&(lastVisibleTab=tab),tab.parent$.show(),activeTab.href!==SHOW_ALL&&tabShouldBeHidden()&&tab!==activeTab?tab.el$.hide():pOptions.hidePreviousTab||tab.el$.css("display","")},hideTabInList=function(tab){activeTab===tab&&(tab.previous?tab.previous.makeActive(!0):tab.next&&tab.next.makeActive(!0)),tab.hidden=!0,tab===firstVisibleTab&&(firstVisibleTab=tab.getNextVisible()),tab===lastVisibleTab&&(lastVisibleTab=tab.getPreviousVisible()),tab.parent$.hide()};if(pOptions.addMoveNextPrevious){var movePrevious$=$('<button type="button" class="apex-rds-previous-region apex-rds-button" title="Previous" aria-label="Previous"><span class="a-Icon icon-left-chevron" aria-hidden="true"></span></button>'),moveNext$=$('<button type="button" class="apex-rds-next-region apex-rds-button" title="Next" aria-label="Next"><span class="a-Icon icon-right-chevron" aria-hidden="true"></span></button>');tabsContainer$.parent().prepend(movePrevious$).append(moveNext$),movePrevious$.click(function(){movePrevious(activeTab)}),moveNext$.click(function(){moveNext(activeTab)})}pJumpMode&&tabsContainer$.addClass("apex-rds-container--jumpNav"),tabsContainer$.css({"white-space":"nowrap","overflow-x":"hidden"});var timeoutLocationHash,links$=$("a",tabsContainer$),previousTab=null,lastVisibleTab=null;if(links$.length<=2&&links$.eq(0).attr("href")==SHOW_ALL)return void tabsContainer$.remove();rtlMode&&(links$=$(links$.get().reverse())),links$.each(function(index){var link$=$(this),href=link$.attr("href"),tabEl$=$(href);if(tabEl$.attr("role","tabpanel"),href==SHOW_ALL&&pJumpMode)return void link$.parent().css("display","none");link$.attr("role","presentation");var scrollToTab=function(pTab){if(!inFillMode()&&pUseSlider&&!displayIsTable()){for(var leftAdjust=-tabsContainer$.width()/2,left=pTab.getLeft()/2,previous=pTab;null!=previous.previous;)previous=previous.previous,left+=previous.parent$.outerWidth();tabsContainer$.stop(!0,!0).animate({scrollLeft:left+leftAdjust},function(){})}},forceActive=function(scrollToActive){if(!this.hidden&&(iterateThroughAndClear(this.previous,!0,href==SHOW_ALL),iterateThroughAndClear(this.next,!1,href==SHOW_ALL),firstVisibleTab.el$.removeClass("apex-rds-swap"),lastVisibleTab.el$.removeClass("apex-rds-swap"),void 0!=timeoutLocationHash&&clearTimeout(timeoutLocationHash),pUseLocationHash&&(timeoutLocationHash=setTimeout(function(){if("history"in window&&window.history&&window.history.pushState)history.pushState(null,null,href);else{var noJumpScroll=$(window).scrollTop();location.hash=href,$(window).scrollTop(noJumpScroll)}},10)),tabShouldBeHidden()&&tabEl$.show(),activeTab=this,activeTab.el$.attr(A_HIDDEN,!1),activeTab.el$.addClass("apex-rds-element-selected"),activeTab.parent$.attr(A_SELECTED,!0),activeTab.link$.removeAttr("tabindex"),scrollToTab(this),link$.parent().removeClass(HINT_CLASS).addClass(SELECTED_CLASS),pUseSessionStorage&&sessionStorage.setItem("activeTab",href),"standard"===pOptions.mode&&(jQuery().stickyWidget&&activeTab.el$.find(".js-stickyWidget-toggle").stickyWidget("refresh"),jQuery().fullCalendar&&activeTab.el$.find(".fc").fullCalendar("rerenderEvents")),tabsContainer$.trigger("rdsregionchange",[activeTab,pOptions.mode]),onRegionChange(pOptions.mode,activeTab),scrollToActive)){var offset=0;if(pJumpMode){var top=currentTab.getTop();offset=apex.theme.defaultStickyTop(),isAnimating=!0,$("html,body").stop(!0,!0).animate({scrollTop:top-offset},{duration:"slow",step:function(position,tween){var end=currentTab.getTop()-offset;end!=tween.end&&(tween.end=end)},complete:function(){isAnimating=!1,pUseLocationHash&&(location.hash=href)}})}else offset=tabsContainer$.data("showAllScrollOffset")(),offset!==!1&&window.scrollTo(0,offset)}},currentTab=tabs[href]={href:href,el$:tabEl$,link$:link$,parent$:link$.parent(),forceActive:forceActive,makeActive:function(userWantsScroll){activeTab!==this&&forceActive.call(this,userWantsScroll)},getNextVisible:function(reverse){return reverse?currentTab.getPreviousVisible():getFirstUnhiddenTab(currentTab.next,"next")},getPreviousVisible:function(reverse){return reverse?currentTab.getNextVisible():getFirstUnhiddenTab(currentTab.previous,"previous")},getTop:function(){return tabEl$.offset().top},getLeft:function(){return this.parent$.offset().left},showHint:function(){for(var previous=this.previous,next=this.next;null!=previous;)previous.parent$.removeClass(HINT_CLASS),previous=previous.previous;for(;null!=next;)next.parent$.removeClass(HINT_CLASS),next=next.next;this.parent$.addClass(HINT_CLASS)},previous:previousTab,next:null};currentTab.parent$.attr(A_CONTROLS,href.substring(1)).attr("role","tab"),location.hash==href&&pUseLocationHash&&(activeTab=currentTab),null==firstVisibleTab&&(firstVisibleTab=currentTab),null!=previousTab&&(previousTab.next=currentTab),previousTab=currentTab,link$.click(function(e){return onClick(currentTab,e),!1}),link$.on("keydown",function(event){var kc=event.which;if(kc===keys.UP)moveNext(currentTab);else if(kc===keys.DOWN)movePrevious(currentTab);else if(kc===keys.PAGE_DOWN);else if(kc===keys.PAGE_UP);else if(kc===keys.RIGHT)moveNext(currentTab);else if(kc===keys.LEFT)movePrevious(currentTab);else if(kc===keys.HOME)firstVisibleTab.link$.click();else if(kc===keys.END)lastVisibleTab.link$.click();else{if(kc!=keys.SPACE)return;currentTab.link$.click()}event.preventDefault()}),currentTab.el$.on("apexaftershow",function(e){e.target===currentTab.el$[0]&&showTabInList(currentTab)}),currentTab.el$.on("apexafterhide",function(e){e.target===currentTab.el$[0]&&hideTabInList(currentTab)}),realNumberOfTabs++});var handleFillModeResize=function(){if(userWantsFillMode()&&pUseSlider){var fixedTabWidth=tabsContainer$.width()/realNumberOfTabs;MIN_TAB_WIDTH>fixedTabWidth?null==slider$&&(tabsContainer$.removeClass(FILL_CLASS),initializeSlider()):null!=slider$&&(tabsContainer$.addClass(FILL_CLASS),slider$.hide())}};if(initializeSlider(),handleFillModeResize(),lastVisibleTab=previousTab,rtlMode){var tempTab=lastVisibleTab;lastVisibleTab=firstVisibleTab,firstVisibleTab=tempTab}if(null==activeTab){if(pUseSessionStorage){var href=sessionStorage.getItem("activeTab");for(var key in tabs)if(tabs[key].href==href){activeTab=tabs[key],pUseLocationHash&&(location.hash=href);break}}null==activeTab&&(activeTab=firstVisibleTab)}tabsContainer$.click(function(){firstVisibleTab.link$.focus()}),$(window).on("apexwindowresized",handleFillModeResize),pJumpMode&&lastVisibleTab.el$.wrap("<div class='apex-rds-last-item-spacer'></div>"),tabsContainer$.data("showAllScrollOffset",function(){return 0})},getScrollOffset=function(){var lookMargin=($(window).scrollTop(),60);return tabsContainer$.offset().top+tabsContainer$.outerHeight()+lookMargin},getNextActiveTabFromSiblings=function(tab){var currentScrollPosition=getScrollOffset(),next=tab.getNextVisible(rtlMode);if(null!=next&&next.getTop()<currentScrollPosition)return next;var previous=tab.getPreviousVisible(rtlMode);return null!=previous&&currentScrollPosition<tab.getTop()?previous:null},initializeStandardTabs=function(){if(buildTabs(),activeTab&&activeTab.href!=SHOW_ALL){var deferredTab=activeTab;setTimeout(function(){deferredTab.forceActive(!0)},250)}var hintedTab=null;$(window).on("scroll",function(){if(activeTab&&activeTab.href==SHOW_ALL){var offset=getScrollOffset();if(null==hintedTab)for(var tab=firstVisibleTab;tab;)!tab.hidden&&tab.href!=SHOW_ALL&&tab.getTop()<offset&&(hintedTab=tab,hintedTab.showHint()),tab=tab.next;else{var nextHintedTab=getNextActiveTabFromSiblings(hintedTab);null!=nextHintedTab&&(hintedTab=nextHintedTab,nextHintedTab.showHint())}}})},initializeJumpNav=function(){if(buildTabs(),activeTab){activeTab.forceActive(!0);var resizeHeight=$(window).height()/3,checkTabs=function(){if(!isAnimating){var tabToMakeActive=getNextActiveTabFromSiblings(activeTab);null!=tabToMakeActive&&tabToMakeActive.makeActive()}};$(window).on("scroll",checkTabs),$(window).on("apexwindowresized",function(){resizeHeight=$(window).height()/3,checkTabs()})}};return pJumpMode?initializeJumpNav():initializeStandardTabs(),{tabs:tabs,moveNext:moveNext,movePrevious:movePrevious,getActiveTab:function(){return activeTab}}}}(apex.widget,apex.jQuery);