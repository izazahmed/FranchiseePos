package franchiseepos

class BlockFilters {
	
    def filters = {
        allExceptTwo(controller:'login|company',invert:true) {
            before = {
				log.info("Inside Before.............")
				log.info("moduleCode..........."+params?.menuCode)
				
				
				if(!session.userId){
					log.info("Inside IF................")
					redirect  (controller: 'login' , action:'auth')
					return false
				} else {
//					if(!(session.menuCodeList).contains(params?.menuCode))
//					{
//						redirect  (controller: 'login' , action:'auth')
//						return false
//					}
				}

            }
        }
    }
}
