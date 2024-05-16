from flask import *
from database import *
admin=Blueprint('admin',__name__)

@admin.route('/adminhome')
def adminhome():
	return render_template('admin.html')

@admin.route('/changepassword', methods=['get','post'])
def changepassword():

	if "submit" in request.form:
		u=request.form['uname']
		r=request.form['password1']
		s=request.form['password']
		q="update login set password='%s' where user_name='%s'"%(s,u)
		update(q)
		return redirect(url_for('admin.adminhome'))

	return render_template('changepassword.html')

@admin.route('/view_reg',methods=['get','post'])
def view_reg():
	data={}
	q="select * from register"
	data['view']=select(q)
	return render_template('view_reg.html',data=data)

@admin.route('/message',methods=['get','post'])
def message():
	data={}
	r=request.args['uid']
	if "submit" in request.form:
		d=request.form['description']
		
		r=request.args['uid']
		q="insert into message values(null,'%s','%s',now(),'pending')"%(r,d)
		insert(q)
		return redirect(url_for('admin.adminhome'))
	s="select * from register"
	data['view']=select(s)


	q="select * from message where user_id=%s"%(r)
	data['show']=select(q)



	

	return render_template('message.html',data=data)


@admin.route('/manage_emergency_users',methods=['get','post'])
def manage_emergency_users():
	data={}
	
	if "submit" in request.form:
		g=request.form['uname']
		j=request.form['password']
		e=request.form['fname']
		i=request.form['lname']
		k=request.form['type']
		r=request.form['phone']
		w=request.form['email']
		p="insert into login values(null,'%s','%s','emergency')"%(g,j)
		log=insert(p)
		q="insert into emergency values(null,'%s','%s','%s','%s','%s','%s')"%(log,e,i,k,r,w)
		insert(q)
		return redirect(url_for('admin.adminhome'))
		
	if 'action' in request.args:
		action=request.args['action']
		emp_id=request.args['uid']
	else:
		action=None

	if action=='delete':
		k="delete from emergency where emergency_id='%s'"%(emp_id)
		print(k)
		delete(k)
		return redirect(url_for('admin.adminhome'))


	if action=='update':
		qu="select * from emergency where emergency_id='%s'"%(emp_id)
		data['up']=select(qu)

		if "update" in request.form:
			n=request.form['fname']
			i=request.form['lname']
			s=request.form['type']
			a=request.form['phone']
			y=request.form['email']
			z="update emergency set first_name='%s',last_name='%s', type='%s',phone='%s',email='%s' where emergency_id='%s'"%(n,i,s,a,y,emp_id)
			update(z)
			return redirect(url_for('admin.adminhome'))



	s="select * from emergency"
	data['view']=select(s)



	return render_template('manage_emergency_users.html',data=data)	

@admin.route('/viewforadmin_emergencylocation',methods=['get','post'])
def viewforadmin_emergencylocation():
	data={} 

	s='select * from emergencies'
	data['view']=select(s)

	return render_template('viewforadmin_emergencylocation.html',data=data)


@admin.route('/location_accidentinformation',methods=['get','post'])
def location_accidentinformation():
	data={}

	if "submit" in request.form:
		y=request.form['place']
		f=request.form['latitude']
		g=request.form['longitude']
		e=request.form['disaster']
		h=request.form['description']
		q="insert into `disaster_news` values(null,now(),'%s','%s','%s','%s','%s')"%(y,f,g,e,h)
		insert(q)

	w="select * from disaster_news"
	data['view']=select(w)

	if 'action' in request.args:
		action=request.args['action']
		o=request.args['nid']
	else:
		action=None


	if action=='delete':
		k="delete from disaster_news where news_id='%s'"%(o)
		print(k)
		delete(k)


	if action=='update':
		qw="select * from disaster_news where news_id='%s'"%(o)
		data['up']=select(qw)
	if 'update' in request.form:

		l=request.form['place']
		i=request.form['latitude']
		n=request.form['longitude']
		a=request.form['disaster']
		k=request.form['description']
		p="update disaster_news set place='%s',latitude='%s', longitude='%s',disaster_type='%s',description='%s' where news_id='%s'"%(l,i,n,a,k)
		update(p)

		s="select * from disaster_news"
		data['view']=select(s)

	



	return render_template('location_accidentinformation.html',data=data)

























# @admin.route('/reg_emergencyusers',methods=['get','post'])
# def reg_emergencyusers():

# 	if "submit" in request.form:
# 		u=request.form['uname']
# 		p=request.form['password']
# 		f=request.form['fname']
# 		l=request.form['lname']
		
# 		h=request.form['no']
# 		e=request.form['mail']
# 		r="insert into login values(null,'%s','%s','user')"%(u,p)
# 		log=insert(r)
# 		q="insert into emergency values(null,'%s','%s','pending','%s','%s')"%(f,l,h,e)
# 		insert(q)


# 	return render_template('reg_emergencyusers.html')


@admin.route('/view_drowsiness')
def view_drowsiness():
	data={}

	w="select * from detection inner join register using(user_id)"
	data['view']=select(w)

	return render_template('view_drowsiness.html',data=data)






@admin.route('/admin_add_ambulance_details',methods=['get','post'])
def admin_add_ambulance_details():
	data={}

	if "submit" in request.form:
		name=request.form['name']
		place=request.form['place']
		phone=request.form['phone']
		email=request.form['email']
		latitude=request.form['latitude']
		longitude=request.form['longitude']
		
		q="insert into `ambulance` values(null,'%s','%s','%s','%s','%s','%s')"%(name,place,phone,email,latitude,longitude)
		insert(q)

	w="select * from ambulance"
	data['view']=select(w)

	if 'action' in request.args:
		action=request.args['action']
		o=request.args['nid']
	else:
		action=None


	if action=='delete':
		k="delete from ambulance where ambulance_id='%s'"%(o)
		print(k)
		delete(k)


	# if action=='update':
	# 	qw="select * from disaster_news where news_id='%s'"%(o)
	# 	data['up']=select(qw)
	# if 'update' in request.form:

	# 	l=request.form['place']
	# 	i=request.form['latitude']
	# 	n=request.form['longitude']
	# 	a=request.form['disaster']
	# 	k=request.form['description']
	# 	p="update disaster_news set place='%s',latitude='%s', longitude='%s',disaster_type='%s',description='%s' where news_id='%s'"%(l,i,n,a,k)
	# 	update(p)

	# 	s="select * from disaster_news"
	# 	data['view']=select(s)



	return render_template('admin_add_ambulance_details.html',data=data)



@admin.route('/admin_add_camra_details',methods=['get','post'])
def admin_add_camra_details():
	data={}

	if "submit" in request.form:
		place=request.form['place']
		types=request.form['type']
		latitude=request.form['latitude']
		longitude=request.form['longitude']
		q="insert into `adddetails` values(null,'%s','%s','%s','%s')"%(place,types,latitude,longitude)
		insert(q)

	w="select * from adddetails"
	data['view']=select(w)

	if 'action' in request.args:
		action=request.args['action']
		o=request.args['nid']
	else:
		action=None

	if action=='delete':
		k="delete from adddetails where details_id='%s'"%(o)
		print(k)
		delete(k)

	return render_template('admin_add_camra_details.html',data=data)



@admin.route('/admin_view_feed_back',methods=['get','post'])
def admin_view_feed_back():
	data={}
	q="select * from register inner join feedback using(user_id)"
	data['view']=select(q)
	return render_template('admin_view_feed_back.html',data=data)












	