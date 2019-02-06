# 2019/02/05 Hiroyuki Ogasawara
# vim:ts=4 sw=4 et:

import  os
import  sys
import  re
import  subprocess


def exec_pipe( command ):
    try:
        with subprocess.Popen( command, stdout=subprocess.PIPE ) as proc:
            data,err= proc.communicate()
    except OSError:
        print( 'Pipe Exec Error' + str(command) )
        sys.stdout.buffer.write( err )
        raise
    return  data.decode( 'utf-8' )



class ApkGetTool:

    def __init__( self ):
        self.package_pat= re.compile( r'^package:(.*)$' )

    def get_package_list( self ):
        command= 'adb shell pm list packages -3'.split()
        result= exec_pipe( command )
        package_list= []
        for line in result.splitlines():
            pat= self.package_pat.search( line )
            if pat is not None:
                package_list.append( pat.group( 1 ) )
        return  package_list

    def get_apk_single( self, file_path, package_name ):
        print( package_name )
        command= 'adb pull'.split()
        command.append( file_path )
        command.append( package_name+'.apk' )
        result= exec_pipe( command )
        print( result )

    def get_apk_all( self, package_list ):
        for package in package_list:
            command= 'adb shell pm path'.split()
            command.append( package )
            result= exec_pipe( command )
            for line in result.splitlines():
                pat= self.package_pat.search( line )
                if pat is not None:
                    file_path= pat.group( 1 )
                    self.get_apk_single( file_path, package )
                    break


def main( argv ):
    acount= len( argv )
    ai= 1
    while ai < acount:
        arg= argv[ai]
        if arg[0] == '-':
            pass
        else:
            pass
        ai+= 1

    tool= ApkGetTool()
    package_list= tool.get_package_list()
    tool.get_apk_all( package_list )
    return  0


if __name__ == '__main__':
    sys.exit( main( sys.argv ) )


