class UrlMappings {

	static mappings = {
		/*"/EmpMst/$id"(controller:"EmpMst", action:"index")*/
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        //"/"(view:"/index")
		"/" ( controller:'login', action:'index' )
		//"/" ( controller:'empMst', action:'index' )
        "500"(view:'/error')
	}
}
