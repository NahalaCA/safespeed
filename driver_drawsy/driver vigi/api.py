from flask import *
from database import *
import uuid
api=Blueprint('api',__name__)

@api.route('/login',methods=['post'])
def login():
    username = request.form['username']
    password = request.form['password']
    latitude=request.form['latitude']
    longi=request.form['longi']
    print("mmmmmmmmmmmmmmmmmmmmmmmmmm",latitude)
    print("llllllllllllllllllllllll",longi)
    q="SELECT * from login where user_name='%s' and password='%s'" % (username,password)
    res = select(q)
    if res :
        return jsonify(status="ok",lid=res[0]["login_id"],ut=res[0]["usertype"])
    else:
        return jsonify(status="no")




# @apiss.route('/user_registration',methods=['post'])
# def user_registration():
#  	u=request.form['username']
#  	pa=request.form['password']
#  	f=request.form['first_name']
# 	l=request.form['last_name']
# 	v=request.form['vehicle_no']
# 	p=request.form['phone']
# 	e=request.form['email']

# 	r="insert into login values(null,'%s','%s','admin')"%(u,pa)
# 	log=insert(r)

# 	f="insert into register values(null,(select login_id from login where login_id='%s'),'%s','%s','%s','%s','%s')"%(f,l,p,e)
# 	res=insert(f)
# 	if res :
# 		return jsonify(status="ok",data=res)
# 	else:
# 		return jsonify(status="no")


@api.route('/user_registration',methods=['post'])
def user_registration():
	u=request.form['user_name']
	pa=request.form['password']
	f=request.form['first_name']
	l=request.form['last_name']
	v=request.form['vehicle_no']
	p=request.form['phone']
	e=request.form['email']

	r="insert into login values(null,'%s','%s','user')"%(u,pa)
	log=insert(r)
	f="insert into register values(null,'%s','%s','%s','%s','%s','%s')"%(log,f,l,v,p,e)
	res=insert(f)
	if res :
		return jsonify(status="ok",data=res)
	else:
		return jsonify(status="no")



@api.route('/user_view_message',methods=['post'])
def user_view_message():
	lid=request.form['lid']
	# p="SELECT * from message"
	p="SELECT * FROM message WHERE user_id=(SELECT `user_id` FROM `register` WHERE `login_id`='%s')"%(lid)
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else:
		return jsonify(status="no")




@api.route('/user_view_disaster',methods=['post'])
def user_view_disaster():
	data={}
	lid=request.form['lid']
	# p="SELECT * from message"
	p="SELECT * FROM disaster_news"
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else:
		return jsonify(status="no")


@api.route('/change_password',methods=['post'])
def change_password():
    username = request.form['username']
    password = request.form['password']

    Password2 = request.form['Password2']
    print(username,password,Password2,"oooooooooooooooooooooooo")
    q="update login set password='%s' where user_name='%s' and password='%s' "%(Password2,username,password)
    res=update(q)
    print(res,"oooooooooooooooooooooooo")
    if res :
        return jsonify(status="ok")
    else:
        return jsonify(status="no")


@api.route('/changepasword_emergency',methods=['post'])
def changepasword_emergency():
    username = request.form['username1']
    password = request.form['password1']
    password2 = request.form['password2']

    print(username,password,password2,"oooooooooooooooooooooooo")
    q="update login set password='%s' where user_name='%s' and password='%s' "%(password2,username,password)
    res=update(q)
    print(res,"oooooooooooooooooooooooo")
    if res :
        return jsonify(status="ok")
    else:
        return jsonify(status="no")




@api.route('/viewdiaster_for_emergency',methods=['post'])
def viewdiaster_for_emergency():
	lid=request.form['lid']
	p="SELECT * FROM disaster_news"
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")




@api.route('/Add_emergency_situatio',methods=['post'])
def Add_emergency_situatio():
	emid=request.form['emid']
	login_id=request.form['lid']
	lati=request.form['lati']
	longi=request.form['longi']
	img1=request.files['pic']
	path="static/images/"+str(uuid.uuid4())+img1.filename
	img1.save(path)
	q="insert into emergencies values(null,(select user_id from register where login_id='%s'),'%s',curdate(),'%s','%s','pending','%s')"%(login_id,emid,lati,longi,path)
	print(q)
	res = insert(q)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")

@api.route('/view_emergency',methods=['post'])
def view_emergency():
	# lid=request.form['lid']
	p="SELECT * FROM emergencies"
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")

@api.route('/update_status',methods=['post'])
def update_status():
	em_id=request.form['em_id']
	p="update emergencies set status='solved' where emergency_id='%s'"%(em_id)
	# print(p)
	res=update(p)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")


from datetime import datetime
@api.route('/add_ditection_face')
def add_ditection_face():
	data={}
	log_id = request.args['log_id']
	now = datetime.now()
	current_hour_minute = now.strftime("%H:%M")
	qr="INSERT INTO `heade_detection` VALUES(NULL,(SELECT `user_id` FROM `register` WHERE `login_id`='%s'),'drowsiness detected on this user',curdate(),'%s')"%(log_id,current_hour_minute)
	res=insert(qr)
	if res:
		data['status']  = 'success'
	else:
		data['status']	= 'failed'
	return str(data)







@api.route('/getnumber')
def getnumber():
	data={}
	lid=request.args['lid']
	lati=request.args['lati']
	longi=request.args['longi']
	# q="select * from contacts where women_id=(select women_id from women where log_id='%s')"%(lid)
	# q="select * from contacts where user_id=(select user_id from user where login_id='%s')"%(lid)
	# print(q)
	# res=select(q)
	q1="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS( latitude ) ))) AS user_distance FROM ambulance HAVING user_distance <40 ORDER BY user_distance ASC " %(lati,longi,lati)
	res1=select(q1)
	if res1:
		data['status']="success"
		data['contact_no']=res1[0]['contact_no']
		data['data']=res1
	else:
		data['status']="failed"
	data['method']="getnumber"
	return str(data)




@api.route('/driverspeed_settings',methods=['post'])
def driverspeed_settings():
	login_id=request.form['lid']
	speed=request.form['speed']
	mode=request.form['mode']
	brightness=request.form['brightness']
	touch=request.form['touch']
	callblock=request.form['callblock']
	automessage=request.form['automessage']
	message=request.form['message']
	messagetype=request.form['messagetype']
	bluetooth=request.form['bluetooth']
	
	q="insert into speed values(null,(select user_id from register where login_id='%s'),'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(login_id,speed,mode,brightness,touch,callblock,automessage,message,messagetype,bluetooth)
	print(q)
	res = insert(q)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")



@api.route('/speedsetting_view/',methods=['post'])
def speedsetting_view():
	lid=request.form['lid']
	speed=request.form['speed']
	p="SELECT * FROM `speed` WHERE `user_id`=(SELECT user_id FROM register WHERE login_id='%s') "%(lid)
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")


@api.route('/partner_view_driverlogs',methods=['post'])
def partner_view_driverlogs():
	# lid=request.form['lid']
	p="SELECT * FROM `speed`"
	print(p)
	res = select(p)
	if res :
		return jsonify(status="ok",data=res)
	else: 
		return jsonify(status="no")


@api.route('/searchplace')
def searchplace():
    data={}
    log_id=request.args['log_id']
    searchitem='%'+request.args['searchitem']+'%'
    print("jjjjjjjjjjjjjjjjjjjjjjjjhh",searchitem)
    q="SELECT * FROM `adddetails` WHERE `type` LIKE '%s' OR `place` LIKE '%s'"%(searchitem,searchitem)
    print(q,"000000000000000000000000000000000000000000000000000")
    res=select(q)
    print(res)  
    data['data']=res
    data['status']='success'
    data['method']='view_places'
    return str(data)



@api.route('/view_places',methods=['get','post'])
def view_places():
	data = {}
	qr="SELECT * FROM `adddetails`"
	print("tttttttttttttttttttttttttttttttttttttttttttt",qr)
	result = select(qr)
	if result:
		data['status'] = 'success'
		data['data'] = result
		data['method'] = 'view_places'
	else:
		data['status'] = 'failed'
		data['method'] = 'view_places'
	return str(data)


@api.route('/usersetalaram',methods=['get','post'])
def usersetalaram():
	data = {}

	lid=request.args['loginid']
	lat=request.args['lati']
	lon=request.args['longi']
	details_id=request.args['pid']
	types=request.args['types']
	qr="insert into notification values (null,(select user_id from register where login_id='%s' ),'%s','%s','%s','%s')"%(lid,details_id,lat,lon,types)
	result = insert(qr)
	print(qr)
	if result:
		data['status'] = 'success'
		data['data'] = result
		data['method'] = 'setalaram'
	else:
		data['status'] = 'failed'
		data['method'] = 'setalaram'
	return str(data)


@api.route('/notification',methods=['get','post'])
def notification():
    
    data={}
    logid=request.args['logid']
    lati=request.args['lati']
    logi=request.args['longi']
    print(lati)
    print(logi)
    q="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(latitude ) ))) AS user_distance  FROM `adddetails`  HAVING user_distance<15.068 " % (lati,logi,lati)
    print(q,"ooooooooooooooooooooooooyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
    res=select(q)
    ss="nearest place are  "
    for row in res:
    	print(row['type'],"ppppppppppppppppppppppppppppppppppppppppppppp")
    	ss=ss+ " Place "+row['place']+ " Type  " +row['type']
    print(ss,"77777777777777777777777777777777777777777777")



    
    if res:
        data['status'] = 'success'
        data['data'] = res
        data['method'] = 'notification'
        data['ss']=ss
    else:
        data['status'] = 'failed'
        data['method'] = 'notification'
    return str(data)


@api.route('/viewreply')
def viewreply():
    data={}
    id=request.args['id']
    q="select * from feedback where user_id=(select user_id from register where login_id='%s')"%(id)
    res=select(q)
    if res:
        data['status']='success'
        data['data']=res
    else:
        data['status']='failed'
    return str(data)

@api.route('/sendcomplaint')
def sendcomplaint():
    data={}
    id=request.args['id']
    complaint=request.args['complaint']
    q="insert into feedback values(null,(select user_id from register where login_id='%s'),'%s',curdate())"%(id,complaint)
    insert(q)
    data['status']='success'
    return str(data)