package com.findingfalcone.feproblem1.utils.common

object Constants {

    const val STRING_BLANK = ""
    const val STRING_COMMA = ","
    const val STRING_COLON = ":"
    const val STRING_COLON_DASH = ":-"
    const val STRING_SPACE = " "
    const val STRING_NEW_LINE = "\n"
    const val STRING_NULL = "null"
    const val STRING_DOT = "."
    const val STRING_SLASH = "/"
    const val STRING_DOT_BIG = "•"
    const val STRING_DASH = "-"
    const val TAG = "HlthCare_APP"

    const val PREFS_NAME = "samedis-care-prefs"

    const val NULL_INDEX = -1L

    const val SPLASH_DISPLAY_LENGTH: Long = 2000

    const val WEB_LOAD_REGISTER = "register"
    const val WEB_LOAD_FORGOT_PASSWORD = "forgot-password"

    const val TENANT_BACK = "tenant_back"
    const val SCAN_QR_CODE = "qr_code/"

    //Image
    const val IMAGE_FORMAT_JPEG: String = "data:image/jpeg;base64,"
    const val IMAGE_FORMAT_PNG = "data:image/png;base64,"

    //Pagination
    const val PAGE_INITIAL_LOAD_SIZE_HINT: Int = 100
    const val PAGE_PREFETCH_DISTANCE: Int = 2
    const val PAGE_SIZE: Int = 10
    interface Pagination{
        companion object{
            const val LIMIT_PER_PAGE: Int = 100
            const val LIMIT_PER_PAGE_MIN: Int = 50
        }
    }

    interface SamedisCareLink{
        companion object{
            const val WEBSITE = "https://www.findingfalcone.feproblem1"
            const val WEBSITE_PRIVACY = "https://www.findingfalcone.feproblem1/datenschutz/"
            const val WEB_URL_QR_CODE = "findingfalcone.feproblem1/api/qr_code/"
            const val WEB_PAG = "https://dev.ident.services/webpage/samedis-care"
        }
    }

    interface Hockey {
        companion object {
            const val HOCKEY_APP_SECRET_ID = "98f1e39e-e03b-4788-834e-44c311f642e4"
            const val HOCKEY_APP_BUILD_TYPE = "BuildType"
            const val HOCKEY_APP_BRANCH = "Branch"
            const val HOCKEY_APP_APP_VERSION = "AppVersion"
        }
    }

    interface DialogAction{
        companion object{
            const val ALERT_ACTION_POSITIVE = "positive"
            const val ALERT_ACTION_NEGATIVE = "negative"
        }
    }

    //Broadcast receiver
    interface ReceiverIntent{
        companion object{
            const val CHANGE_LANGUAGE = "changeLanguage"
        }
    }

    interface IntentExtra{
        companion object{
            const val CAMERA_SCAN_FROM = "camera_scan_from"
            const val CAMERA_SCAN_FROM_URI = "camera_scan_from_URI"
            const val CAMERA_SCAN_FROM_SCAN = "camera_scan_from_SCAN"
            const val TITLE = "title"
            const val SEARCH_KEY = "search_key"
            const val SEARCH_KEY_DEVICE_MODEL = "search_key_device_model"
            const val SEARCH_KEY_LOCATION = "search_key_location"
            const val SEARCH_KEY_DEPARTMENT = "search_key_department"
            const val SEARCH_KEY_SERVICE_PARTNER = "search_key_service_partner"
            const val EDIT_INVENTORY = "edit_inventory"
            const val INVENTORY_ID = "inventory_id"
            const val CATALOG_ID = "catalog_id"
        }
    }

    interface BundleExtra{
        companion object{
            //More
            const val WEB_PAGE_URL = "web_page_url"
            const val MENU_OPEN_IN_ACTIVITY = "menu_open_in_activity"
        }
    }

    interface ActionCode{
        companion object{
            const val TAKE_PHOTO = 400
            const val TAKE_DOCUMENT = 401

            const val PIC_PHOTO_CAMERA = 500
            const val PIC_PHOTO_GALLERY = 501
            const val PIC_DOCUMENT = 500

            const val IMAGE_DELETE = 1001
            const val IMAGE_STAR = 1002
            const val DOC_EYE = 1003
            const val SERVICE_PARTNER_ROW = 1004
            const val SERVICE_PARTNER_DELETE = 1005
            const val MAINTENANCE_EVENT_DELETE = 1006

            const val OPEN_INVENTORY = 1007
        }
    }

    interface DataStatus{
        companion object{
            const val STATUS = "status"
            const val LOCAL = "local"
            const val UPLOAD = "upload"
            const val DELETE = "delete"
            const val ONLINE = "online"
            const val OFFLINE = "offline"
        }
    }

    interface InventoryStep{
        companion object{
            const val ONE = 1
            const val TWO = 2
            const val THREE = 3
            const val FOUR = 4
            const val EDIT = 5
        }
    }

    interface InventorySave{
        companion object{
            const val SERVICE_PARTNER_ADD = 11
            const val SERVICE_PARTNER_DELETE = 12
            const val MAINTENANCE_EVENT_ADD = 13
            const val MAINTENANCE_EVENT_DELETE = 14
            const val IMAGE_ADD = 15
            const val IMAGE_DELETE = 16
            const val DOCUMENT_ADD = 17
            const val DOCUMENT_DELETE = 18
        }
    }

    interface InventoryRelational{
        companion object{
            const val CONTACTS = "contacts"
        }
    }

    interface scanQrCodeType{
        companion object {
            const val QR_CODE_RESOURCE = "qr_code_resource"
            const val INVENTORY_VIEW_USER= "inventory_view_user"
            const val INVENTORY = "inventory"
        }

    }


    //Enum Ref: https://www.geeksforgeeks.org/enum-classes-in-kotlin/
    enum class CE{
        NO,
        YES
    }

    enum class RISK {
        SELF_EXPLANATORY,
        USER_INSTRUCTION,
        MANUFACTURER;
    }

    enum class SEARCH{
        DEVICE_MODEL,
        LOCAtION,
        DEPARTMENT
    }

}