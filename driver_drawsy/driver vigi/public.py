from flask import *
from database import *
public=Blueprint('public',__name__)

@public.route('/')
def home():
	return render_template('index.html')


@public.route('/carregister',methods=['get','post'])
def carregister():
	if "submit" in request.form:
		u=request.form['uname']
		p=request.form['password']
		f=request.form['fname']
		l=request.form['lname']
		v=request.form['no']
		h=request.form['no']
		e=request.form['mail']
		r="insert into login values(null,'%s','%s','admin')"%(u,p)
		log=insert(r)
		q="insert into register values(null,'%s','%s','%s','%s','%s','%s')"%(log,f,l,v,h,e)
		insert(q)


	return render_template('carregister.html')

@public.route('/login',methods=['get','post'])
def login():

	if "submit" in request.form:
		u=request.form['uname']
		p=request.form['password']

		a="select * from login where user_name='%s'and password='%s'"%(u,p)
		res=select(a)
		if res:
			session['login_id']=res[0]['login_id']
			if res[0]['usertype']=='admin':
				return redirect(url_for('admin.adminhome'))

			elif res[0]['usertype']=='emergency':
				q1="select * from emergency where login_id='%s'"%(session['login_id'])
				res1=select(q1)
				if res1:
					session['emergency_id']=res1[0]['emergency_id']
				return redirect(url_for('emergency.emergencyhome'))

			elif res[0]['usertype']=='user':
				q2="select * from register where login_id='%s'"%(session['login_id'])
				res3=select(q2)
				if res3:
					session['user_id']=res3[0]['user_id']
				return redirect(url_for('carmodule.carhome'))
		else:
			flash("invalid user name or password")


	return render_template('login.html')



	






